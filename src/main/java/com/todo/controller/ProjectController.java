package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todo.domain.Project;
import com.todo.service.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService service;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Project add(@RequestParam String projectName, @RequestParam String projectDesc) {

		Project newProject = new Project();
		
		newProject.setProjectName(projectName);
		newProject.setProjectDesc(projectDesc);

		return service.add(newProject);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody
	Project edit(@RequestParam String projectName, @RequestParam String projectDesc) {

		Project editProject = new Project();
		
		editProject.setProjectName(projectName);
		editProject.setProjectDesc(projectDesc);

		return service.edit(editProject);
	}

}
