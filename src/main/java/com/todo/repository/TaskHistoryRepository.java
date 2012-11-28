package com.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.TaskHistory;

public interface TaskHistoryRepository extends MongoRepository<TaskHistory, String> {

	List<TaskHistory> findTaskByTaskid(String taskid);
}