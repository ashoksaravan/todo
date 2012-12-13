package com.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.UserProject;

public interface UserProjectRepository extends MongoRepository<UserProject, String> {

	List<UserProject> findUserProjectByUsername(String username);
}