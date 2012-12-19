package com.todo.domain;

import java.util.ArrayList;

/**
 * @author vinodkumara
 * 
 */
public class SelectedUserProject {

	/**
	 * username.
	 */
	private String username;
	/**
	 * project.
	 */
	private ArrayList<Project> project;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the project
	 */
	public ArrayList<Project> getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(ArrayList<Project> project) {
		this.project = project;
	}


}
