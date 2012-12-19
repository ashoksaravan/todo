package com.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.User;

/**
 * @author vinodkumara
 *
 */
public interface UserRepository extends MongoRepository<User, String> {

	/**
	 * @param username
	 * @return User
	 */
	User findByUsername(String username);
}
