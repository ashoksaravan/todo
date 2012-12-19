package com.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.Priority;

/**
 * @author vinodkumara
 *
 */
public interface PriorityRepository extends MongoRepository<Priority, String>{

}
