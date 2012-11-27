package com.todo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.domain.Task;
import com.todo.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> readAll(String username) {
		return taskRepository.findTaskByUsername(username);
	}
	
	public Task addEditTask(Task task) {
		Task existingTask = taskRepository.findTaskByTaskid(task.getTaskid());
		if (existingTask == null) {
			task.setTaskid(UUID.randomUUID().toString());
			taskRepository.save(task);
			return task;
		}else{
			existingTask.setTaskid(task.getTaskid());
			existingTask.setTaskname(task.getTaskname());
			existingTask.setTaskdesc(task.getTaskdesc());
			existingTask.setPriority(task.getPriority());
			existingTask.setTaskstatus(task.getTaskstatus());
			existingTask.setUsername(task.getUsername());
			existingTask.setCreateduser(task.getCreateduser());
			existingTask.setCclist(task.getCclist());
			taskRepository.save(existingTask);
		}
		return existingTask;
	}

}
