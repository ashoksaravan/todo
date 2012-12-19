package com.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.UserProject;

/**
 * @author vinodkumara
 *
 */
public interface UserProjectRepository extends MongoRepository<UserProject, String> {

	/**
	 * @param username
	 * @return List<UserProject>
	 */
	List<UserProject> findUserProjectByUsername(String username);
}