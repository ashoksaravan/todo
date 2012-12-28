package com.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vinodkumara
 *
 */
@Controller
@RequestMapping
public class AccessController {

	/**
	 * @param model
	 * @param message
	 * @return String
	 */
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required = false) String message) {
		model.addAttribute("message", message);
		return "loginmanager";
	}

	/**
	 * @return String
	 */
	@RequestMapping(value = "/denied")
	public String denied() {
		return "denied";
	}

	/**
	 * @return String
	 */
	@RequestMapping(value = "/login/failure")
	public String loginFailure() {
		String message = "The username or password you entered is incorrect";
		return "redirect:/login?message=" + message;
	}

	/**
	 * @param message
	 * @return String
	 */
	@RequestMapping(value = "/logout/success")
	public String logoutSuccess(@RequestParam(required = false) String message) {
		return "redirect:/login";
	}

	/**
	 * @param oldpwd
	 * @param newPwd
	 * @param confirmPwd
	 * @return String
	 */
	@RequestMapping(value = "/requestNewPwd")
	public String changePassword(@RequestParam String oldpwd, @RequestParam String newPwd,
			@RequestParam String confirmPwd) {
		return "taskmanager";
	}
}