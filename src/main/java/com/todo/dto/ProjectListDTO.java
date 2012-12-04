package com.todo.dto;

import java.util.List;

import com.todo.domain.Project;

public class ProjectListDTO {
	
private List<Project> project;
	
	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}

}
