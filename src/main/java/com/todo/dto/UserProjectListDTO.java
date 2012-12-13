package com.todo.dto;

import java.util.List;

import com.todo.domain.UserProject;

public class UserProjectListDTO {
	
private List<UserProject> userProject;
	
	public List<UserProject> getUserProject() {
		return userProject;
	}

	public void setUserProject(List<UserProject> userProject) {
		this.userProject = userProject;
	}

}
