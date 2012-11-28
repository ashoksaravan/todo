package com.todo.dto;

import java.util.List;

import com.todo.domain.TaskHistory;

public class TaskHistoryListDTO {

	private List<TaskHistory> taskHistory;

	public List<TaskHistory> getTaskHistory() {
		return taskHistory;
	}

	public void setTaskHistory(List<TaskHistory> taskHistory) {
		this.taskHistory = taskHistory;
	}


}