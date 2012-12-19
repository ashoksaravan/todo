package com.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.TaskHistory;

/**
 * @author vinodkumara
 *
 */
public interface TaskHistoryRepository extends MongoRepository<TaskHistory, String> {

	/**
	 * @param taskid
	 * @return List<TaskHistory>
	 */
	List<TaskHistory> findTaskByTaskid(String taskid);
}