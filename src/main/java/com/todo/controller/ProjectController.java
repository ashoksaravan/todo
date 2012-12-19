package com.todo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.command.AddEditUserProjectCmd;
import com.todo.command.AssociateProjectCmd;
import com.todo.domain.Project;
import com.todo.domain.SelectedUserProject;
import com.todo.domain.User;
import com.todo.domain.UserProject;
import com.todo.dto.ProjectListDTO;
import com.todo.service.ProjectService;
import com.todo.service.RefDataService;

/**
 * @author vinodkumara
 *
 */
@Controller
@RequestMapping("/projects")
@SessionAttributes({ "user" })
public class ProjectController {

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
	 * associateProjectCmd.
	 */
	@Autowired
	AssociateProjectCmd associateProjectCmd;

	/**
	 * addEditUserProjectCmd.
	 */
	@Autowired
	AddEditUserProjectCmd addEditUserProjectCmd;

	/**
	 * @param projectName
	 * @param projectDesc
	 * @return Project
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Project add(@RequestParam String projectName, @RequestParam String projectDesc) {

		Project newProject = new Project();
		newProject.setProjectName(projectName);
		newProject.setProjectDesc(projectDesc);

		return service.add(newProject);
	}

	/**
	 * @param projectName
	 * @param projectDesc
	 * @return Project
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody
	Project edit(@RequestParam String projectName, @RequestParam String projectDesc) {

		Project editProject = new Project();
		editProject.setProjectName(projectName);
		editProject.setProjectDesc(projectDesc);

		return service.edit(editProject);
	}

	/**
	 * @param username
	 * @return ProjectListDTO
	 */
	@RequestMapping(value = "/userProj", method = RequestMethod.POST)
	public @ResponseBody
	ProjectListDTO userProjectList(@RequestParam String username) {
		return associateProjectCmd.associateUserProject(username);
	}

	/**
	 * @param data
	 * @return Boolean
	 */
	@RequestMapping(value = "/addEditUserProj", method = RequestMethod.POST)
	public @ResponseBody
	Boolean addEditUserProjectList(@RequestBody SelectedUserProject data) {
		return addEditUserProjectCmd.addEditUserProject(data);
	}

	/**
	 * @param user
	 * @return ProjectListDTO
	 */
	@RequestMapping(value = "/refdataUserProject", method = RequestMethod.POST)
	public @ResponseBody
	ProjectListDTO userProjectList(@ModelAttribute User user,HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		ProjectListDTO listDTO = new ProjectListDTO();
		ArrayList<Integer> list = new ArrayList<Integer>();
		List<Project> result = new ArrayList<Project>();
		@SuppressWarnings("unchecked")
		List<Project> projects = (List<Project>) context.getAttribute("projectList");
		List<UserProject> useProjects = service.readProject(user.getUsername());
		for (UserProject userProject : useProjects) {
			list.add(userProject.getProjectId());
		}
		for (Project project : projects) {
			if (list.contains(project.getProjectId())) {
				result.add(project);
			}
		}
		listDTO.setProject(result);
		return listDTO;
	}

}
