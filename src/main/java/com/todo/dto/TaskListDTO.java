package com.todo.dto;

import java.util.List;

import com.todo.domain.Task;

public class TaskListDTO {

	private List<Task> tasks;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
