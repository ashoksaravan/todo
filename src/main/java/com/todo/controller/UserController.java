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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.command.ForgotPasswordCmd;
import com.todo.domain.Role;
import com.todo.domain.User;
import com.todo.dto.UserListDTO;
import com.todo.service.RefDataService;
import com.todo.service.UserService;

/**
 * @author vinodkumara
 *
 */
@Controller
@RequestMapping("/users")
@SessionAttributes({ "user" })
public class UserController {

	/**
	 * service.
	 */
	@Autowired
	private UserService service;

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
	 * @return UserListDTO
	 */
	@RequestMapping(value = "/records")
	public @ResponseBody
	UserListDTO getUsers(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
		UserListDTO userListDto;
		User user = new User();
		user.setUsername(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		if ((userName != null && userName.trim().length() > 0) || (firstName != null && firstName.trim().length() > 0)
				|| (lastName != null && lastName.trim().length() > 0)) {
			userListDto = new UserListDTO();
			List<User> users = service.searchUser(user);
			userListDto.setUsers(users);
			return userListDto;
		} else {
			userListDto = new UserListDTO();
			userListDto.setUsers(service.readAll());
			return userListDto;

		}
	}

	/**
	 * @param username
	 * @return Boolean
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
	 * @return User
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
	 * @return User
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
	 * @return Boolean
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Boolean delete(@RequestParam String username) {

		User existingUser = new User();
		existingUser.setUsername(username);

		return service.delete(existingUser);
	}

	/**
	 * @param username
	 * @param confirmpass
	 * @return Boolean
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
	 * @return Boolean
	 */
	@RequestMapping(value = "/checkpwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean checkpwd(@RequestParam String username, @RequestParam String pwd) {

		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setPassword(pwd);

		return service.checkPwd(existingUser);
	}

	/**
	 * @param username
	 * @return Boolean
	 */
	@RequestMapping(value = "/forgotpwd", method = RequestMethod.POST)
	public @ResponseBody
	Boolean forgotpwd(@RequestParam String username) {
		return forgotPasswordCmd.forgotPassword(username);
	}

	/**
	 * @param model
	 * @param user
	 * @param oldPwd
	 * @param newPwd
	 * @return String
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