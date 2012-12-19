package com.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.TaskStatus;

/**
 * @author vinodkumara
 *
 */
public interface TaskStatusRepository extends MongoRepository<TaskStatus, String>{

}
