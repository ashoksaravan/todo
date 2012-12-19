package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.command.AddEditTaskCmd;
import com.todo.command.EditTaskHistoryCmd;
import com.todo.domain.Task;
import com.todo.dto.TaskHistoryListDTO;
import com.todo.service.TaskService;

/**
 * @author vinodkumara
 *
 */
@Controller
@RequestMapping("/taskservice")
@SessionAttributes({ "tasks" })
public class TaskController {
	
	/**
	 * taskService.
	 */
	@Autowired
	private TaskService taskService;
	
	/**
	 * addEditTaskCmd.
	 */
	@Autowired
	AddEditTaskCmd addEditTaskCmd;
	
	/**
	 * editTaskHistoryCmd.
	 */
	@Autowired
	EditTaskHistoryCmd editTaskHistoryCmd;

	/**
	 * @param map
	 * @param projectId
	 * @return List<Task>
	 */
	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public @ResponseBody
	List<Task> task(ModelMap map, @RequestParam Integer projectId) {
		Task task = new Task();
		task.setProjectId(projectId);
		task.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Task> tasks = taskService.loadTask(task);
		map.addAttribute("tasks", tasks);
		return tasks;
	}

	/**
	 * @param addEditTask
	 * @return Boolean
	 */
	@RequestMapping(value = "/addtask", method = RequestMethod.POST)
	public @ResponseBody
	Boolean addEditTask(@RequestBody Task addEditTask) {
		Task task = addEditTaskCmd.addEditTask(addEditTask);
		return editTaskHistoryCmd.editTaskHistory(task);
	}

	/**
	 * @param taskid
	 * @return TaskHistoryListDTO
	 */
	@RequestMapping(value = "/history", method = RequestMethod.POST)
	public @ResponseBody
	TaskHistoryListDTO taskHistory(@RequestParam String taskid) {
		TaskHistoryListDTO historyListDTO = new TaskHistoryListDTO();
		historyListDTO.setTaskHistory(taskService.readTaskVersion(taskid));
		return historyListDTO;
	}

	/**
	 * @param map
	 * @param searchTask
	 * @return List<Task>
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody
	List<Task> searchTask(ModelMap map, @RequestBody Task searchTask) {
		List<Task> tasks = taskService.search(searchTask);
		map.addAttribute("tasks", tasks);
		return tasks;
	}
}
