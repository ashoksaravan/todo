package com.todo.controller;

import java.util.ArrayList;
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
import com.todo.command.SearchUserCmd;
import com.todo.command.UserCmd;
import com.todo.domain.Role;
import com.todo.domain.User;
import com.todo.dto.UserListDTO;
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
	 * forgotPasswordCmd.
	 */
	@Autowired
	ForgotPasswordCmd forgotPasswordCmd;

	/**
	 * searchUserCmd.
	 */
	@Autowired
	SearchUserCmd searchUserCmd;

	/**
	 * userCmd.
	 */
	@Autowired
	UserCmd userCmd;

	/**
	 * @return UserListDTO
	 */
	@RequestMapping(value = "/records")
	public @ResponseBody
	UserListDTO getUsers(@RequestParam(required = false) Integer rows, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) String searchField, @RequestParam(required = false) String searchOper,
			@RequestParam(required = false) String searchString, @RequestParam(required = false) boolean _search) {
		UserListDTO userListDto = new UserListDTO();
		List<User> users = new ArrayList<User>();
		if (_search) {
			users = searchUserCmd.searchUsers(searchField, searchOper, searchString);
		} else {
			users = service.readAll();
		}
		if (rows != null && rows != 0 && page != null && page != 0 && users != null && users.size() > 0) {
			List<User> viewRows = new ArrayList<User>();
			for (int i = (rows * (page - 1)); i < (rows * page); i++) {
				viewRows.add(users.get(i));
				if (i == (users.size() - 1)) {
					break;
				}
			}
			userListDto.setRows(viewRows);
			userListDto.setTotal((users.size() / rows) + 1);
		} else {
			userListDto.setRows(users);
		}
		userListDto.setRecords(users.size());
		return userListDto;
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
	String update(Model model, ModelMap map, @RequestParam(required = false) String username,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
			@RequestParam(required = false, value = "role.desc") Integer role,
			@RequestParam(required = false) String mailId, @RequestParam String oper, @RequestParam String id) {

		Role roleIn = new Role();
		roleIn.setRole(role);
		try {
			if (firstName == "" || lastName == "" || mailId == "") {
				throw new Exception("Invalid Data. Please correct the data and proceed");
			}

			if ("edit".equalsIgnoreCase(oper)) {
				User existingUser = new User();
				existingUser.setUsername(id);
				existingUser.setFirstName(firstName);
				existingUser.setLastName(lastName);
				existingUser.setMailId(mailId);
				existingUser.setRole(roleIn);
				existingUser.setReqNewPwd(Boolean.FALSE);
				service.update(existingUser);
				if (SecurityContextHolder.getContext().getAuthentication().getName().equals(existingUser.getUsername())) {
					map.addAttribute("user", existingUser);
				}
				return "users";
			} else if ("add".equalsIgnoreCase(oper)) {
				User newUser = new User();
				newUser.setUsername(userCmd.getUserName(mailId));
				newUser.setFirstName(firstName);
				newUser.setLastName(lastName);
				newUser.setRole(roleIn);
				newUser.setMailId(mailId);
				newUser.setReqNewPwd(Boolean.TRUE);
				newUser.setPassword(userCmd.getPassword(newUser));
				service.create(newUser);
				return "users";
			} else if ("del".equalsIgnoreCase(oper)) {
				User existingUser = new User();
				existingUser.setUsername(id);
				service.delete(existingUser);
				return "users";
			}

		} catch(Exception exception){
			return "users";
		}
		return null;
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