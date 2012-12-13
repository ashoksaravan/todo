package com.todo.command;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.domain.Project;
import com.todo.domain.UserProject;
import com.todo.dto.ProjectListDTO;
import com.todo.service.ProjectService;
import com.todo.service.RefDataService;

@Service
public class AssociateProjectCmd {

	/**
	 * dataService.
	 */
	@Autowired
	private RefDataService dataService;

	/**
	 * service.
	 */
	@Autowired
	private ProjectService service;

	/**
	 * @param username
	 * @return
	 */
	public ProjectListDTO associateUserProject(String username) {
		ArrayList<Integer> projectIds = new ArrayList<Integer>();
		ProjectListDTO listDTO = new ProjectListDTO();
		List<Project> listProjects = dataService.readProject();
		List<UserProject> list = service.readProject(username);
		for (UserProject userProject : list) {
			projectIds.add(userProject.getProjectId());
		}
		for (Project project : listProjects) {
			if (projectIds.contains(project.getProjectId())) {
				project.setChecked(Boolean.TRUE);
			}
		}
		listDTO.setProject(listProjects);
		return listDTO;
	}

}
