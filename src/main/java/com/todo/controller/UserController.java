package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todo.domain.Role;
import com.todo.domain.Task;
import com.todo.domain.User;
import com.todo.dto.TaskListDTO;
import com.todo.dto.UserListDTO;
import com.todo.service.TaskService;
import com.todo.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	static String  name = new String();
	@Autowired
	private UserService service;
	@Autowired
	private TaskService taskService;

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
			@RequestParam Integer role, @RequestParam String userstatus,
			@RequestParam String mailId) {

		Role newRole = new Role();
		newRole.setRole(role);

		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setRole(newRole);
		newUser.setUserStatus(userstatus);
		newUser.setMailId(mailId);

		return service.create(newUser);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	User update(@RequestParam String username, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam Integer role) {

		Role existingRole = new Role();
		existingRole.setRole(role);

		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setFirstName(firstName);
		existingUser.setLastName(lastName);
		existingUser.setRole(existingRole);

		return service.update(existingUser);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Boolean delete(@RequestParam String username) {

		User existingUser = new User();
		existingUser.setUsername(username);

		return service.delete(existingUser);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	String login(@RequestParam String username, @RequestParam String password) {
		User user = service.read(username);
		if (user != null) {
			if (password.equals(user.getPassword())) {
				return user.getUsername();
			} else {
				return "login failed";
			}
		} else {
			return "login failed";
		}
	}
	
	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public @ResponseBody
	TaskListDTO task(@RequestParam String username) {
		TaskListDTO taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(taskService.readAll(username));
		return taskListDTO;
	}
	
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean changepwd(@RequestParam String username,
			@RequestParam String oldpass, @RequestParam String confirmpass) {

		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(confirmpass);

		return service.updatePwd(existingUser);
	}
}
