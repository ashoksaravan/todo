package com.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.Project;

public interface ProjectRepository extends MongoRepository<Project, String>{

	Project findByProjectName(String projectName);
}
