package com.todo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.domain.Role;
import com.todo.domain.Task;
import com.todo.domain.User;
import com.todo.dto.TaskListDTO;
import com.todo.dto.UserListDTO;
import com.todo.service.TaskService;
import com.todo.service.UserService;

@Controller
@RequestMapping("/users")
@SessionAttributes({ "user" })
public class UserController {

	static String name = new String();

	static String password = new String();
	@Autowired
	private UserService service;
	@Autowired
	private TaskService taskService;
	
	private static String NEW = "NEW";
	private static String CANCEL = "CANCEL";
	private static String HOLD = "HOLD";
	private static String COMPLETED = "COMPLETED";

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
			@RequestParam Integer role, @RequestParam String status,
			@RequestParam String mailID) {

		Role newRole = new Role();
		newRole.setRole(role);

		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setRole(newRole);
		newUser.setUserStatus(status);
		newUser.setMailId(mailID);

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
	Boolean login(@RequestParam String username, @RequestParam String password,
			ModelMap map) {
		User user = service.read(username);
		if (user != null) {
			if (password.equals(user.getPassword())) {
				map.addAttribute(user);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public @ResponseBody
	TaskListDTO task(@ModelAttribute User user) {
		TaskListDTO taskListDTO = new TaskListDTO();
		taskListDTO.setTasks(taskService.readAll(user.getUsername()));
		return taskListDTO;
	}

	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean changepwd(@RequestParam String username,
			@RequestParam String confirmpass) {

		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(confirmpass);

		return service.updatePwd(existingUser);
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
	String addEditTask(@RequestParam String taskid,
			@RequestParam String taskname, @RequestParam String taskdesc,
			@RequestParam String priority, @RequestParam String taskstatus,
			@RequestParam String username, @RequestParam String createduser) {
		
		String subject = new String();
		String desc = new String();
		Task task = new Task();
		task.setTaskid(taskid);
		task.setTaskname(taskname);
		task.setTaskdesc(taskdesc);
		task.setPriority(priority);
		task.setTaskstatus(taskstatus);
		task.setUsername(username);
		task.setCreateduser(createduser);
		Task newTask = taskService.addEditTask(task);
		User user = new User();
		user.setUsername(username);
		user = service.read(user);
		User author = new User();
		author.setUsername(createduser);
		author = service.read(author);
		ArrayList<String> ar = new ArrayList<String>();
		ar.add(user.getMailId());
		if(!ar.contains(author.getMailId())){
			ar.add(author.getMailId());
		}
		subject = taskname+"\tAssigned to: "+username+ "\tStatus: "+taskstatus;
		if(taskstatus.equals(COMPLETED)){
			desc = "The Task has been completed.";
		}else if(taskstatus.equals(HOLD)){
			desc = "The Task is hold.";
		}else if(taskstatus.equals(CANCEL)){
			desc = "The Task has been cancelled.";
		}else if(taskstatus.equals(NEW)){
			desc = "A New Task has been assigned.";
		}else{
			desc = "A Task has been assigned for development.";
		}

		SendMail sms = new SendMail();
		sms.Sendmail(desc + "\n\n"
				+ "Task Description : " + taskdesc , ar, subject);
		return newTask.getUsername();
	}

	@RequestMapping(value = "/forgotpwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean forgotpwd(@RequestParam String username) {

		User user = new User();
		GenerateRandomPassword randomPassword = new GenerateRandomPassword();
		user = service.read(username);
		if (user != null) {
			password = randomPassword.getAlphaNumeric(10);
			ArrayList<String> ar = new ArrayList<String>();
			ar.add(user.getMailId());
			user.setPassword(password);
			Boolean pwdUpdate = service.updatePwd(user);
			if (pwdUpdate) {
				SendMail sms = new SendMail();
				sms.Sendmail("Password for user '" + username + "' is '"
						+ password +"'.", ar, "Change Password");
				return true;
			}
		}
		return false;
	}
}
