package com.todo.dto;

import java.util.List;

import com.todo.domain.Task;

/**
 * @author vinodkumara
 *
 */
public class TaskListDTO {

	/**
	 * tasks.
	 */
	private List<Task> tasks;

	/**
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
