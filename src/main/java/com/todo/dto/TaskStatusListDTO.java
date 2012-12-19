package com.todo.dto;

import java.util.List;

import com.todo.domain.TaskStatus;

/**
 * @author vinodkumara
 * 
 */
public class TaskStatusListDTO {

	/**
	 * taskStatus.
	 */
	private List<TaskStatus> taskStatus;

	/**
	 * @return the taskStatus
	 */
	public List<TaskStatus> getTaskStatus() {
		return taskStatus;
	}

	/**
	 * @param taskStatus the taskStatus to set
	 */
	public void setTaskStatus(List<TaskStatus> taskStatus) {
		this.taskStatus = taskStatus;
	}

}
