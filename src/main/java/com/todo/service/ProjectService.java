package com.todo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.domain.Project;
import com.todo.domain.UserProject;
import com.todo.repository.ProjectRepository;
import com.todo.repository.UserProjectRepository;

@Service
public class ProjectService {

	/**
	 * projectRepository.
	 */
	@Autowired
	private ProjectRepository projectRepository;

	/**
	 * userProjectRepository.
	 */
	@Autowired
	private UserProjectRepository userProjectRepository;

	/**
	 * dataService.
	 */
	@Autowired
	private RefDataService dataService;

	/**
	 * @param project
	 * @return
	 */
	public Project add(Project project) {

		project.setId(UUID.randomUUID().toString());
		List<Project> projects = dataService.readProject();
		if (projects != null && projects.size() > 0) {
			project.setProjectId(projects.size() + 1);
		} else {
			project.setProjectId(1);
		}
		project.getProjectName();
		project.getProjectDesc();
		return projectRepository.save(project);

	}

	/**
	 * @param project
	 * @return
	 */

	public Project edit(Project project) {

		Project editProject = projectRepository.findByProjectName(project.getProjectName());

		if (editProject == null) {
			return null;
		}

		editProject.setProjectName(project.getProjectName());
		editProject.setProjectDesc(project.getProjectDesc());
		projectRepository.save(editProject);

		return editProject;
	}

	/**
	 * @param username
	 * @return
	 */
	public List<UserProject> readProject(String username) {
		return userProjectRepository.findUserProjectByUsername(username);
	}

	/**
	 * @param project
	 * @param username
	 */
	public void addUserProject(Project project, String username) {
		UserProject userProject = new UserProject();
		userProject.setId(UUID.randomUUID().toString());
		userProject.setProjectId(project.getProjectId());
		userProject.setUsername(username);
		userProjectRepository.save(userProject);
	}

	/**
	 * @param userProject
	 */
	public void deleteUserProject(UserProject userProject) {
		userProjectRepository.delete(userProject);
	}

}
