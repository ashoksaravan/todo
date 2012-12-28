package com.todo.service;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.todo.domain.Role;
import com.todo.domain.User;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}
 */
public class InitMongoService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void init() {

		Collection<Role> roles = mongoTemplate.findAll(Role.class);
		if (roles.size() == 0) {
			// Create new records
			Role adminRole = new Role();
			adminRole.setId(UUID.randomUUID().toString());
			adminRole.setRole(1);
			adminRole.setDesc("Admin");

			Role userRole = new Role();
			userRole.setId(UUID.randomUUID().toString());
			userRole.setRole(2);
			userRole.setDesc("Regular");

			User admin = new User();
			admin.setId(UUID.randomUUID().toString());
			admin.setFirstName("Admin");
			admin.setLastName("Admin");
			admin.setPassword(passwordEncoder("admin", "admin"));
			admin.setRole(adminRole);
			admin.setUsername("admin");
			admin.setMailId("admin@todo.com");
			admin.setReqNewPwd(Boolean.FALSE);

			// Insert to db
			mongoTemplate.insert(admin, "user");
			mongoTemplate.insert(adminRole, "role");
			mongoTemplate.insert(userRole, "role");
		}
	}

	/**
	 * @param password
	 * @param salt
	 * @return
	 */
	private String passwordEncoder(String password, String salt) {
		PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(password, salt);
	}
}
