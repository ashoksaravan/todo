package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes({ "user" })
public class MediatorController {

	/**
	 * service.
	 */
	@Autowired
	private UserService service;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String getHomePage(ModelMap model) {
		model.addAttribute("user", service.read(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "taskmanager";
	}
}
