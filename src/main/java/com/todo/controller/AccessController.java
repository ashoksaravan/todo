package com.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AccessController {

	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required=false) String message) {
		model.addAttribute("message", message);
		return "loginmanager";
	}
	
	@RequestMapping(value = "/denied")
 	public String denied() {
		return "denied";
	}
	
	@RequestMapping(value = "/login/failure")
 	public String loginFailure() {
		String message = "The username or password you entered is incorrect";
		return "redirect:/login?message="+message;
	}
	
	@RequestMapping(value = "/logout/success")
 	public String logoutSuccess(@RequestParam(required=false) String message) {
		return "redirect:/login";
	}

	@RequestMapping(value = "/requestNewPwd")
	public String changePassword(@RequestParam String oldpwd, @RequestParam String newPwd, @RequestParam String confirmPwd){
		System.out.println(oldpwd + newPwd + confirmPwd);
		return "taskmanager";
	}
}