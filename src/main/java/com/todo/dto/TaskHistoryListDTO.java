package com.todo.dto;

import java.util.List;

import com.todo.domain.TaskHistory;

/**
 * @author vinodkumara
 *
 */
public class TaskHistoryListDTO {

	/**
	 * taskHistory.
	 */
	private List<TaskHistory> taskHistory;

	/**
	 * @return the taskHistory
	 */
	public List<TaskHistory> getTaskHistory() {
		return taskHistory;
	}

	/**
	 * @param taskHistory the taskHistory to set
	 */
	public void setTaskHistory(List<TaskHistory> taskHistory) {
		this.taskHistory = taskHistory;
	}

}