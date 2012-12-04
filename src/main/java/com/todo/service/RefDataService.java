package com.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.domain.Priority;
import com.todo.domain.TaskStatus;
import com.todo.repository.PriorityRepository;
import com.todo.repository.TaskStatusRepository;

@Service
public class RefDataService {
	
	@Autowired
	private PriorityRepository priorityRepository;
	
	@Autowired
	private TaskStatusRepository taskStatusRepository;
	
	public List<Priority> readPriority() {
		return priorityRepository.findAll();
	}
	
	public List<TaskStatus> readTaskStatus() {
		return taskStatusRepository.findAll();
	}

}
