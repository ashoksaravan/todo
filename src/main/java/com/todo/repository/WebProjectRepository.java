/**
 * 
 */
package com.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.WebProject;

/**
 * @author vinodkumara
 *
 */
public interface WebProjectRepository extends MongoRepository<WebProject, String> {
	
	/**
	 * @param projectName
	 * @return Project
	 */
	WebProject findByProjectName(String projectName);
	
	/**
	 * @param projectName
	 * @return Project
	 */
	List<WebProject> findWebProjectByUsername(String username);

}
