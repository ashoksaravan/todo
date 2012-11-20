package com.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

	List<Task> findTaskByUsername(String username);
}
