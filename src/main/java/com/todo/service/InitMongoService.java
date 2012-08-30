package com.todo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.todo.domain.Role;
import com.todo.domain.User;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}
 */
public class InitMongoService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void init() {
		// Drop existing collections
		mongoTemplate.dropCollection("role");
		mongoTemplate.dropCollection("user");

		// Create new records
		Role adminRole = new Role();
		adminRole.setId(UUID.randomUUID().toString());
		adminRole.setRole(1);

		Role userRole = new Role();
		userRole.setId(UUID.randomUUID().toString());
		userRole.setRole(2);

		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setFirstName("Vinodkumar");
		user.setLastName("Arunachalam");
		user.setPassword("vinod");
		user.setRole(adminRole);
		user.setUsername("vinod");
		user.setMailId("vinod@gmail.com");
		user.setUserStatus("ACTIVE");

		User user1 = new User();
		user1.setId(UUID.randomUUID().toString());
		user1.setFirstName("Ashok");
		user1.setLastName("Mani");
		user1.setPassword("ashok");
		user1.setRole(userRole);
		user1.setUsername("ashok");
		user1.setMailId("ashok@gmail.com");
		user1.setUserStatus("ACTIVE");

		// Insert to db
		mongoTemplate.insert(user, "user");
		mongoTemplate.insert(user1, "user");
		mongoTemplate.insert(adminRole, "role");
		mongoTemplate.insert(userRole, "role");
	}
}
