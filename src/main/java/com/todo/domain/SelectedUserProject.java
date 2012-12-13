package com.todo.domain;

import java.util.ArrayList;

public class SelectedUserProject {
	
	private String username;
	private ArrayList<Project> project;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ArrayList<Project> getProject() {
		return project;
	}
	public void setProject(ArrayList<Project> project) {
		this.project = project;
	}

}
