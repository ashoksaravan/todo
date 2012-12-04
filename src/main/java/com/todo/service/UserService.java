package com.todo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.domain.Role;
import com.todo.domain.User;
import com.todo.repository.RoleRepository;
import com.todo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public User create(User user) {
		Role role = null;
		user.setId(UUID.randomUUID().toString());
		if (user.getRole().getRole() != null) {
			role = roleRepository.findByRole(user.getRole().getRole());
		}
		user.getRole().setId(role.getId().toString());
		user.setPassword(passwordEncoder(user.getPassword(), user.getUsername()));

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
		userRepository.save(existingUser);
		return existingUser;
	}

	public Boolean delete(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());

		if (existingUser == null) {
			return false;
		}
		userRepository.delete(existingUser);
		return true;
	}

	public Boolean updatePwd(User user, boolean fromForgotPwd) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser == null) {
			return false;
		}
		if(fromForgotPwd){
			existingUser.setReqNewPwd(Boolean.TRUE);
		}
		existingUser.setPassword(passwordEncoder(user.getPassword(), user.getUsername()));
		userRepository.save(existingUser);
		return true;

	}

	public Boolean checkPwd(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser.getPassword().equals(passwordEncoder(user.getPassword(), user.getUsername()))) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to encode the password using MD5.
	 * @param password password
	 * @return encodePassword
	 */
	private String passwordEncoder(String password, String salt) {
		PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(password, salt);
	}
}
