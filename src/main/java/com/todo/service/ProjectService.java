package com.todo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.domain.Project;
import com.todo.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private RefDataService dataService;
	
	public Project add(Project project) {

		project.setId(UUID.randomUUID().toString());
		List<Project> projects = dataService.readProject();
		if(projects != null && projects.size() > 0){
			project.setProjectId(projects.size() + 1);
		}else{
			project.setProjectId(1);
		}
		project.getProjectName();
		project.getProjectDesc();
		return projectRepository.save(project);
	}

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
}
