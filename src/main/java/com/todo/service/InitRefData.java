package com.todo.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.todo.domain.Acf2Id;
import com.todo.domain.Priority;
import com.todo.domain.Project;
import com.todo.domain.TaskStatus;

/**
 * @author vinodkumara
 *
 */
@Service
public class InitRefData implements ServletContextAware {
	
	/**
	 * dataService.
	 */
	@Autowired
	private RefDataService dataService;
	
	/**
	 * list.
	 */
	private List<Priority> priorityList;
	
	/**
	 * taskStatusList.
	 */
	private List<TaskStatus> taskStatusList;
	
	/**
	 * projectList.
	 */
	private List<Project> projectList;
	
	/**
	 * acf2IdList.
	 */
	private List<Acf2Id> acf2IdList;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext context) {
		priorityList = dataService.readPriority();
		context.setAttribute("priorityList", priorityList);
		
		taskStatusList = dataService.readTaskStatus();
		context.setAttribute("taskStatusList", taskStatusList);
		
		projectList = dataService.readProject();
		context.setAttribute("projectList", projectList);
		
		acf2IdList = dataService.readAcf2Id();
		context.setAttribute("acf2IdList", acf2IdList);
		
	}
}