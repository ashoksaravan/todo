package com.todo.dto;

import java.util.List;

import com.todo.domain.Priority;

/**
 * @author vinodkumara
 *
 */
public class PriorityListDTO {
	
	/**
	 * priority.
	 */
	private List<Priority> priority;

	/**
	 * @return the priority
	 */
	public List<Priority> getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(List<Priority> priority) {
		this.priority = priority;
	}
	
	

}
