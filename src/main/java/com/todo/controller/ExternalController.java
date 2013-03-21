package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.todo.service.WebtimeService;

/**
 * @author vinodkumara
 *
 */
@Controller
@RequestMapping("/external")
public class ExternalController {
	/**
	 * service.
	 */
	@Autowired
	private WebtimeService service;


	/**
	 * @param data
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/export.xls", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView export() {
		return new ModelAndView("ExcelRevenueSummary");
	}
	
	/**
	 * @param data
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/exportTimeSheet.xls", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView exportTimeSheet() {
		return new ModelAndView("ExcelRevenueSummary1");
	}

}
