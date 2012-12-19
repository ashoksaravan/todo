package com.todo.dto;

import java.util.List;

import com.todo.domain.UserProject;

/**
 * @author vinodkumara
 * 
 */
public class UserProjectListDTO {

	/**
	 * userProject.
	 */
	private List<UserProject> userProject;

	/**
	 * @return the userProject
	 */
	public List<UserProject> getUserProject() {
		return userProject;
	}

	/**
	 * @param userProject the userProject to set
	 */
	public void setUserProject(List<UserProject> userProject) {
		this.userProject = userProject;
	}

}
