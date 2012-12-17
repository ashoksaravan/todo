package com.todo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.domain.Role;
import com.todo.domain.User;
import com.todo.repository.RoleRepository;
import com.todo.repository.UserRepository;

@Service
public class UserService {

	/**
	 * userRepository.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * roleRepository.
	 */
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * mongoTemplate.
	 */
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * @param user
	 * @return
	 */
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

	/**
	 * @param user
	 * @return
	 */
	public User read(User user) {
		return userRepository.findByUsername(user.getUsername());
	}

	/**
	 * @param userName
	 * @return
	 */
	public User read(String userName) {
		return userRepository.findByUsername(userName);
	}

	/**
	 * @return
	 */
	public List<User> readAll() {
		return userRepository.findAll();
	}

	/**
	 * @param user
	 * @return
	 */
	public User update(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());

		if (existingUser == null) {
			return null;
		}

		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.getRole().setRole(user.getRole().getRole());
		existingUser.setReqNewPwd(user.getReqNewPwd());
		existingUser.setPassword(user.getPassword());
		userRepository.save(existingUser);
		return existingUser;
	}

	/**
	 * @param user
	 * @return
	 */
	public Boolean delete(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());

		if (existingUser == null) {
			return false;
		}
		userRepository.delete(existingUser);
		return true;
	}

	/**
	 * @param user
	 * @param fromForgotPwd
	 * @return
	 */
	public Boolean updatePwd(User user, boolean fromForgotPwd) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser == null) {
			return false;
		}
		if (fromForgotPwd) {
			existingUser.setReqNewPwd(Boolean.TRUE);
		}
		existingUser.setPassword(passwordEncoder(user.getPassword(), user.getUsername()));
		userRepository.save(existingUser);
		return true;

	}

	/**
	 * @param user
	 * @return
	 */
	public Boolean checkPwd(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser.getPassword().equals(passwordEncoder(user.getPassword(), user.getUsername()))) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to encode the password using MD5.
	 */

	/**
	 * @param password
	 * @param salt
	 * @return
	 */
	private String passwordEncoder(String password, String salt) {
		PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(password, salt);
	}

	/**
	 * @param userName
	 * @return
	 */
	public Boolean checkUsername(String userName) {
		User user = userRepository.findByUsername(userName);
		if (user == null) {
			return false;
		}
		return true;
	}
	
	public List<User> searchUser(User user) {
		Criteria criteria = null;
		
		if (user.getUsername() != null
				&& user.getUsername().trim().length() > 0) {
			criteria = Criteria.where("username").regex(user.getUsername());
		}
		
		if (user.getFirstName() != null
				&& user.getFirstName().trim().length() > 0 && criteria != null) {
			criteria = criteria.and("firstName").regex(user.getFirstName());
		} else {
			if (user.getFirstName() != null
					&& user.getFirstName().trim().length() > 0) {
				criteria = Criteria.where("firstName").regex(user.getFirstName());
			}
		}
		
		if (user.getLastName() != null
				&& user.getLastName().trim().length() > 0 && criteria != null) {
			criteria = criteria.and("lastName").regex(user.getLastName());
		} else {
			if (user.getLastName() != null
					&& user.getLastName().trim().length() > 0) {
				criteria = Criteria.where("lastName").regex(user.getLastName());
			}
		}
		List<User> list = mongoTemplate.find(new Query(criteria), User.class);
		return list;
		
	}
}
