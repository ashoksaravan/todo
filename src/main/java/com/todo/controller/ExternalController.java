package com.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author vinodkumara
 *
 */
@Controller
@RequestMapping("/external")
public class ExternalController {


	/**
	 * @param data
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView export() {
		return new ModelAndView("ExcelRevenueSummary");
	}

}
