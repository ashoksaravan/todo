package com.todo.dto;

import java.util.List;

import com.todo.domain.TaskStatus;

public class TaskStatusListDTO {
	
private List<TaskStatus> taskStatus;
	
	public List<TaskStatus> getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(List<TaskStatus> taskStatus) {
		this.taskStatus = taskStatus;
	}

}
