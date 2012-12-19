package com.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.Project;

/**
 * @author vinodkumara
 *
 */
public interface ProjectRepository extends MongoRepository<Project, String> {

	/**
	 * @param projectName
	 * @return Project
	 */
	Project findByProjectName(String projectName);

}
