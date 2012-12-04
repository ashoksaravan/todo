package com.todo.dto;

import java.util.List;

import com.todo.domain.Priority;

public class PriorityListDTO {
	
	private List<Priority> priority;
	
	public List<Priority> getPriority() {
		return priority;
	}

	public void setPriority(List<Priority> priority) {
		this.priority = priority;
	}

}
