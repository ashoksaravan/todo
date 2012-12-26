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

/**
 * @author vinodkumara
 *
 */
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
	 * @return User
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
	 * @return User
	 */
	public User read(User user) {
		return userRepository.findByUsername(user.getUsername());
	}

	/**
	 * @param userName
	 * @return User
	 */
	public User read(String userName) {
		return userRepository.findByUsername(userName);
	}

	/**
	 * @return List<User>
	 */
	public List<User> readAll() {
		return userRepository.findAll();
	}

	/**
	 * @param user
	 * @return User
	 */
	public User update(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		Role role = null;

		if (existingUser == null) {
			return null;
		}

		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		if (user.getRole().getRole() != null) {
			role = roleRepository.findByRole(user.getRole().getRole());
		}
		existingUser.getRole().setId(role.getId().toString());
		existingUser.setReqNewPwd(user.getReqNewPwd());
		existingUser.setPassword(user.getPassword());
		existingUser.setMailId(user.getMailId());
		userRepository.save(existingUser);
		return existingUser;
	}

	/**
	 * @param user
	 * @return Boolean
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
	 * @return Boolean
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
	 * @return Boolean
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
	 * @return String
	 */
	private String passwordEncoder(String password, String salt) {
		PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(password, salt);
	}
}
