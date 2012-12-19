package com.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.Task;

/**
 * @author vinodkumara
 *
 */
public interface TaskRepository extends MongoRepository<Task, String> {

	/**
	 * @param username
	 * @return List<Task>
	 */
	List<Task> findTaskByUsername(String username);
	
	/**
	 * @param taskid
	 * @return Task
	 */
	Task findTaskByTaskid(String taskid);
}
