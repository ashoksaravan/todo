package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.todo.dto.TaskHistoryListDTO;
import com.todo.dto.TaskStatusListDTO;
import com.todo.dto.UserListDTO;
import com.todo.service.RefDataService;
import com.todo.service.TaskService;
import com.todo.service.UserService;

@Controller
@RequestMapping("/users")
@SessionAttributes({"user", "tasks"})
public class UserController {

	/**
	 * service.
	 */
	@Autowired
	private UserService service;

	/**
	 * taskService.
	 */
	@Autowired
	private TaskService taskService;

	/**
	 * dataService.
	 */
	@Autowired
	private RefDataService dataService;

	/**
	 * forgotPasswordCmd.
	 */
	@Autowired
	ForgotPasswordCmd forgotPasswordCmd;

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
	 * @return
	 */
	@RequestMapping(value = "/records")
	public @ResponseBody
	UserListDTO getUsers(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName) {
		User user = new User();
		user.setUsername(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		if ((userName != null && userName.trim().length() > 0)
				|| (firstName != null && firstName.trim().length() > 0)
				|| (lastName != null && lastName.trim().length() > 0)) {
			UserListDTO userListDto = new UserListDTO();
			userListDto.setUsers(service.searchUser(user));
			return userListDto;
		} else {
			UserListDTO userListDto = new UserListDTO();
			userListDto.setUsers(service.readAll());
			return userListDto;

		}
	}

	/**
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/getName")
	public @ResponseBody
	Boolean get(@RequestParam String username) {
		return service.checkUsername(username);
	}

	/**
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param role
	 * @param mailID
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody
	User create(@RequestParam String username, @RequestParam String password, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam Integer role, @RequestParam String mailID) {

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

	/**
	 * @param map
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param role
	 * @param mailId
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	User update(ModelMap map, @RequestParam String username, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam Integer role, @RequestParam String mailId) {

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

		if (SecurityContextHolder.getContext().getAuthentication().getName().equals(existingUser.getUsername())) {
			map.addAttribute("user", existingUser);
		}

		return existingUser;
	}

	/**
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Boolean delete(@RequestParam String username) {

		User existingUser = new User();
		existingUser.setUsername(username);

		return service.delete(existingUser);
	}

	//need to moved to task controller
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
	 * @param username
	 * @param confirmpass
	 * @return
	 */
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean changepwd(@RequestParam String username, @RequestParam String confirmpass) {

		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(confirmpass);

		return service.updatePwd(existingUser, false);
	}

	/**
	 * @param username
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/checkpwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean checkpwd(@RequestParam String username, @RequestParam String pwd) {

		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(pwd);

		return service.checkPwd(existingUser);
	}

	//need to moved to task controller
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

	//need to moved to task controller
	@RequestMapping(value = "/history", method = RequestMethod.POST)
	public @ResponseBody
	TaskHistoryListDTO taskHistory(@RequestParam String taskid) {
		TaskHistoryListDTO historyListDTO = new TaskHistoryListDTO();
		historyListDTO.setTaskHistory(taskService.readTaskVersion(taskid));
		return historyListDTO;
	}

	//need to moved to task controller
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody
	List<Task> searchTask(ModelMap map, @RequestBody Task searchTask) {
		List<Task> tasks = taskService.search(searchTask);
		map.addAttribute("tasks", tasks);
		return tasks;
	}
	//need to moved to task controller
	/**
	 * @return
	 */
	@RequestMapping(value = "/refdataPriority", method = RequestMethod.POST)
	public @ResponseBody
	PriorityListDTO priorityList() {
		PriorityListDTO listDTO = new PriorityListDTO();
		listDTO.setPriority(dataService.readPriority());
		return listDTO;
	}

	//need to moved to task controller
	/**
	 * @return
	 */
	@RequestMapping(value = "/refdataTaskStatus", method = RequestMethod.POST)
	public @ResponseBody
	TaskStatusListDTO taskStatusList() {
		TaskStatusListDTO listDTO = new TaskStatusListDTO();
		listDTO.setTaskStatus(dataService.readTaskStatus());
		return listDTO;
	}
	
	/**
	 * @param model
	 * @param user
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(Model model, @ModelAttribute User user, @RequestParam String oldPwd,
			@RequestParam String newPwd) {
		PasswordEncoder encoder = new Md5PasswordEncoder();
		if (user.getPassword().equals(encoder.encodePassword(oldPwd, user.getUsername()))) {
			user.setPassword(encoder.encodePassword(newPwd, user.getUsername()));
			user.setReqNewPwd(Boolean.FALSE);
			service.update(user);
			model.addAttribute("message", "Password Changed Successfully");
			return "redirect:/logout";
		} else {
			model.addAttribute("message", "Wrong Old Password!");
			return "changepassword";
		}
	}
}