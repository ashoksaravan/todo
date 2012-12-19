package com.todo.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todo.domain.Project;
import com.todo.dto.ProjectListDTO;

/**
 * @author vinodkumara
 *
 */
@Controller
@RequestMapping("/metaData")
public class MasterDataController {
	
	/**
	 * @return ProjectListDTO
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/readProjects", method = RequestMethod.POST)
	public @ResponseBody
	ProjectListDTO projectList(HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		ProjectListDTO listDTO = new ProjectListDTO();
		listDTO.setProject((List<Project>)context.getAttribute("projectList"));
		return listDTO;
	}

}
