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
import com.todo.command.ForgotPasswordCmd;
import com.todo.domain.Role;
import com.todo.domain.Task;
import com.todo.domain.User;
import com.todo.dto.PriorityListDTO;
import com.todo.dto.ProjectListDTO;
import com.todo.dto.TaskHistoryListDTO;
import com.todo.dto.TaskListDTO;
import com.todo.dto.TaskStatusListDTO;
import com.todo.dto.UserListDTO;
import com.todo.service.RefDataService;
import com.todo.service.TaskService;
import com.todo.service.UserService;

@Controller
@RequestMapping("/users")
@SessionAttributes({ "user" })
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RefDataService dataService;
	
	@Autowired
	ForgotPasswordCmd forgotPasswordCmd;

	@Autowired
	AddEditTaskCmd addEditTaskCmd;

	@Autowired
	EditTaskHistoryCmd editTaskHistoryCmd;
	
	@RequestMapping
	public String getUsersPage() {
		return "users";
	}

	@RequestMapping(value = "/records")
	public @ResponseBody
	UserListDTO getUsers() {

		UserListDTO userListDto = new UserListDTO();
		userListDto.setUsers(service.readAll());
		return userListDto;
	}

	@RequestMapping(value = "/get")
	public @ResponseBody
	User get(@RequestBody User user) {
		return service.read(user);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody
	User create(@RequestParam String username, @RequestParam String password,
			@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam Integer role,
			@RequestParam String mailID) {

		Role newRole = new Role();
		newRole.setRole(role);

		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setRole(newRole);
		newUser.setMailId(mailID);

		return service.create(newUser);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	User update(ModelMap map, @RequestParam String username, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam Integer role, 
			@RequestParam String mailId) {

		Role existingRole = new Role();
		existingRole.setRole(role);

		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setFirstName(firstName);
		existingUser.setLastName(lastName);
		existingUser.setMailId(mailId);
		existingUser.setRole(existingRole);
		existingUser.setReqNewPwd(Boolean.FALSE);
		
		service.update(existingUser);

		if(SecurityContextHolder
				.getContext().getAuthentication().getName().equals(existingUser.getUsername())){
			map.addAttribute("user", existingUser);
		}

		return existingUser;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Boolean delete(@RequestParam String username) {

		User existingUser = new User();
		existingUser.setUsername(username);

		return service.delete(existingUser);
	}

	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public @ResponseBody
	TaskListDTO task() {
		TaskListDTO taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(taskService.readAll(SecurityContextHolder
				.getContext().getAuthentication().getName()));
		return taskListDTO;
	}

	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean changepwd(@RequestParam String username,
			@RequestParam String confirmpass) {

		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(confirmpass);

		return service.updatePwd(existingUser, false);
	}

	@RequestMapping(value = "/checkpwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean checkpwd(@RequestParam String username, @RequestParam String pwd) {

		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(pwd);

		return service.checkPwd(existingUser);
	}
	
	@RequestMapping(value = "/addtask", method = RequestMethod.POST)
	public @ResponseBody
	Boolean addEditTask(@RequestBody Task addEditTask) {
		Task task = addEditTaskCmd.addEditTask(addEditTask);
		return editTaskHistoryCmd.editTaskHistory(task);
	}

	@RequestMapping(value = "/forgotpwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean forgotpwd(@RequestParam String username) {
		return forgotPasswordCmd.forgotPassword(username);
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.POST)
	public @ResponseBody
	TaskHistoryListDTO taskHistory(@RequestParam String taskid) {
		TaskHistoryListDTO historyListDTO = new TaskHistoryListDTO();
		historyListDTO.setTaskHistory(taskService.readTaskVersion(taskid));
		return historyListDTO;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody
	List<Task> searchTask(@RequestBody Task searchTask) {
		return taskService.search(searchTask);
	}
	
	@RequestMapping(value = "/refdataPriority", method = RequestMethod.POST)
	public @ResponseBody
	PriorityListDTO priorityList() {
		PriorityListDTO listDTO = new PriorityListDTO();
		listDTO.setPriority(dataService.readPriority());
		return listDTO;
	}
	
	@RequestMapping(value = "/refdataTaskStatus", method = RequestMethod.POST)
	public @ResponseBody
	TaskStatusListDTO taskStatusList() {
		TaskStatusListDTO listDTO = new TaskStatusListDTO();
		listDTO.setTaskStatus(dataService.readTaskStatus());
		return listDTO;
	}
	
	@RequestMapping(value = "/refdataProject", method = RequestMethod.POST)
	public @ResponseBody
	ProjectListDTO projectList() {
		ProjectListDTO listDTO = new ProjectListDTO();
		listDTO.setProject(dataService.readProject());
		return listDTO;
	}

}
