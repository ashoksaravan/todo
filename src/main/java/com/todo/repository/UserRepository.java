package com.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);
}
