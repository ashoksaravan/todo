/**
 * 
 */
package com.todo.dto;

import java.util.List;

import com.todo.domain.WebProject;

/**
 * @author vinodkumara
 *
 */
public class WebProjectListDTO {

	/**
	 * project.
	 */
	private List<WebProject> webProjects;

	/**
	 * @return the webProjects
	 */
	public List<WebProject> getWebProjects() {
		return webProjects;
	}

	/**
	 * @param webProjects the webProjects to set
	 */
	public void setWebProjects(List<WebProject> webProjects) {
		this.webProjects = webProjects;
	}
}
