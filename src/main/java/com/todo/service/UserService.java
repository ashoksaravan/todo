package com.todo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.domain.User;
import com.todo.repository.RoleRepository;
import com.todo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public User create(User user) {
		user.setId(UUID.randomUUID().toString());
		user.getRole().setId(UUID.randomUUID().toString());

		// We must save both separately since there is no cascading feature
		// in Spring Data MongoDB (for now)
		roleRepository.save(user.getRole());
		return userRepository.save(user);
	}

	public User read(User user) {
		return userRepository.findByUsername(user.getUsername());
	}

	public User read(String userName) {
		return userRepository.findByUsername(userName);
	}

	public List<User> readAll() {
		return userRepository.findAll();
	}

	public User update(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());

		if (existingUser == null) {
			return null;
		}

		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.getRole().setRole(user.getRole().getRole());

		// We must save both separately since there is no cascading feature
		// in Spring Data MongoDB (for now)
		roleRepository.save(existingUser.getRole());
		return userRepository.save(existingUser);
	}

	public Boolean delete(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());

		if (existingUser == null) {
			return false;
		}

		// We must delete both separately since there is no cascading feature
		// in Spring Data MongoDB (for now)
		roleRepository.delete(existingUser.getRole());
		userRepository.delete(existingUser);
		return true;
	}
	
	public Boolean updatePwd(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser == null) {
			return false;
		}
		existingUser.setPassword(user.getPassword());
		userRepository.save(existingUser);
		return true;
		
	}
}
