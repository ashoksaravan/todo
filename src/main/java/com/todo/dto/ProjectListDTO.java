package com.todo.dto;

import java.util.List;

import com.todo.domain.Project;

/**
 * @author vinodkumara
 * 
 */
public class ProjectListDTO {

	/**
	 * project.
	 */
	private List<Project> project;

	/**
	 * @return the project
	 */
	public List<Project> getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(List<Project> project) {
		this.project = project;
	}

	
}
