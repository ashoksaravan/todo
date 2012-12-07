package com.todo.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todo.command.WriteExcelCmd;
import com.todo.domain.Priority;
import com.todo.domain.Project;
import com.todo.domain.Task;
import com.todo.service.RefDataService;

@Controller
@RequestMapping("/external")
public class ExternalController {
	
	@Autowired
	private RefDataService dataService;
	
	private List<Priority> priorities;
	private List<Project> project;
	private HashMap<String, String> map = new HashMap<String, String>();

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public @ResponseBody
	Boolean export(@RequestBody Task[] data) {
		WriteExcelCmd writeExcelCmd = new WriteExcelCmd();
		writeExcelCmd.writeExcelFile(data,getRefData());
		return true;
	}
	
	private HashMap<String, String> getRefData(){
		priorities = dataService.readPriority();
		for (Iterator<Priority> iterator = priorities.iterator(); iterator.hasNext();) {
			Priority priority = (Priority) iterator.next();
			map.put(priority.getValue(), priority.getDesc());
			
		}
		project = dataService.readProject();
		for (Iterator<Project> iterator = project.iterator(); iterator.hasNext();) {
			Project project = (Project) iterator.next();
			map.put(project.getProjectId().toString(), project.getProjectDesc());
		}
		return map;
		
	}

}
