package com.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.Role;

public interface RoleRepository extends MongoRepository<Role, Integer> {
	
	Role findByRole(Integer role);

}
