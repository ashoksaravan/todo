/**
 * 
 */
package com.todo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.domain.TimeSheetDetail;
import com.todo.domain.User;
import com.todo.domain.WebProject;
import com.todo.dto.TimeSheetDetailListDTO;
import com.todo.dto.WebProjectListDTO;
import com.todo.repository.TimeSheetDetailRepository;
import com.todo.service.WebtimeService;

/**
 * @author vinodkumara
 * 
 */
@Controller
@RequestMapping("/webtime")
@SessionAttributes({ "user", "datetime", "timesheet", "header" })
public class WebtimeController {

	/**
	 * service.
	 */
	@Autowired
	private WebtimeService service;

	/**
	 * projectRepository.
	 */
	@Autowired
	private TimeSheetDetailRepository sheetDetailRepository;

	/**
	 * month_names.
	 */
	private static final String[] month_names = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec" };

	/**
	 * day_names.
	 */
	private static final String[] day_names = new String[] { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

	/**
	 * @param projectName
	 * @param user
	 * @return WebProject
	 */
	@RequestMapping(value = "/addwebproj", method = RequestMethod.POST)
	public @ResponseBody
	WebProject addnewwebproj(@RequestParam String projectName, @ModelAttribute User user) {
		WebProject newProject = new WebProject();
		newProject.setProjectName(projectName);
		newProject.setUsername(user.getUsername());
		return service.add(newProject);
	}

	/**
	 * @param projectName
	 * @param user
	 * @return WebProject
	 */
	@RequestMapping(value = "/updatewebproj", method = RequestMethod.POST)
	public @ResponseBody
	WebProject updatewebproj(@RequestParam String projectName, @RequestParam String oldValue, @ModelAttribute User user) {
		WebProject project = new WebProject();
		project.setProjectName(projectName);
		project.setUsername(user.getUsername());
		return service.edit(project, oldValue);
	}

	/**
	 * @param user
	 * @param request
	 * @return WebProjectListDTO
	 */
	@RequestMapping(value = "/webprojRecords", method = RequestMethod.POST)
	public @ResponseBody
	WebProjectListDTO userwebProjectRecords(@ModelAttribute User user, HttpServletRequest request) {
		WebProjectListDTO listDTO = new WebProjectListDTO();
		listDTO.setWebProjects(service.readProject());
		return listDTO;
	}

	/**
	 * @param map
	 * @param rows
	 * @param page
	 * @param searchField
	 * @param searchOper
	 * @param searchString
	 * @param _search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/readtimesheet")
	public @ResponseBody
	TimeSheetDetailListDTO getTimeSheetDetail(ModelMap map, @RequestParam(required = false) Integer rows,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) String searchField,
			@RequestParam(required = false) String searchOper, @RequestParam(required = false) String searchString,
			@RequestParam(required = false) boolean _search, HttpServletRequest request, @ModelAttribute User user) {
		HttpSession session = request.getSession();
		TimeSheetDetailListDTO listDTO = new TimeSheetDetailListDTO();
		List<TimeSheetDetail> details = new ArrayList<TimeSheetDetail>();
		ArrayList<String> strings = new ArrayList<String>();
		if (_search) {
			// users = searchUserCmd.searchUsers(searchField, searchOper,
			// searchString);
		} else {
			strings = (ArrayList<String>) session.getAttribute("datetime");
			for (String string : strings) {
				List<TimeSheetDetail> timeSheetDetail = service.query(string, user.getUsername());
				if (timeSheetDetail.size() == 0) {
					TimeSheetDetail detail = new TimeSheetDetail();
					detail.setDate(string);
					detail.setUsername(user.getUsername());
					detail.setProjectName("");
					detail.setDesc("");
					detail.setHrs(0);
					details.add(detail);
				} else {
					for (TimeSheetDetail detail : timeSheetDetail) {
						details.add(detail);
					}
				}
			}
		}
		if (rows != null && rows != 0 && page != null && page != 0 && details != null && details.size() > 0) {
			List<TimeSheetDetail> viewRows = new ArrayList<TimeSheetDetail>();
			for (int i = (rows * (page - 1)); i < (rows * page); i++) {
				viewRows.add(details.get(i));
				if (i == (details.size() - 1)) {
					break;
				}
			}
			listDTO.setRows(viewRows);
			listDTO.setTotal((details.size() / rows) + 1);
		} else {
			listDTO.setRows(details);
		}
		if (details != null)
			map.addAttribute("timesheet", details);
		listDTO.setRecords(details.size());
		return listDTO;
	}

	/**
	 * @param searchTask
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/timeSheeturl", method = RequestMethod.POST)
	public @ResponseBody
	Boolean query(@RequestParam boolean users, @RequestParam Date from, @RequestParam Date to,
			@RequestParam Integer projectId, ModelMap map, @ModelAttribute User user) throws Exception {
		ArrayList<String> not_day = new ArrayList<String>();
		not_day.add("Sun");
		not_day.add("Sat");
		ArrayList<String> list = new ArrayList<String>();
		// TimeSheetDetailListDTO listDTO = new TimeSheetDetailListDTO();
		List<TimeSheetDetail> details = new ArrayList<TimeSheetDetail>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String fromDt = sdf.format(from);
		cal.setTime(sdf.parse(fromDt));
		fromDt = sdf.format(cal.getTime());
		if (!not_day.contains(day_names[cal.get(Calendar.DAY_OF_WEEK) - 1])) {
			String fromStr = (day_names[cal.get(Calendar.DAY_OF_WEEK) - 1] + ' ' + month_names[cal.get(Calendar.MONTH)]
					+ ' ' + cal.get(Calendar.DATE) + ' ' + cal.get(Calendar.YEAR));
			list.add(fromStr);
		}
		while (!cal.getTime().equals(to)) {
			cal.add(Calendar.DATE, 1);
			if (!not_day.contains(day_names[cal.get(Calendar.DAY_OF_WEEK) - 1])) {
				String newStr = (day_names[cal.get(Calendar.DAY_OF_WEEK) - 1] + ' '
						+ month_names[cal.get(Calendar.MONTH)] + ' ' + cal.get(Calendar.DATE) + ' ' + cal
						.get(Calendar.YEAR));
				list.add(newStr);
			}
		}
		details = service.queryWebTime(list, users, user.getUsername(), projectId);
		for (TimeSheetDetail timeSheetDetail : details) {
			if (timeSheetDetail.getProjectName() != null) {
				timeSheetDetail.setBuffer(timeSheetDetail.getProjectName() + '-' + timeSheetDetail.getDesc() + '-'
						+ timeSheetDetail.getHrs() + "hrs");
			}
		}
		if (details.size() > 0) {
			map.addAttribute("timesheet", details);
		}
		map.addAttribute("header", list);
		return true;
	}

	/**
	 * @param model
	 * @param map
	 * @param date
	 * @param projectName
	 * @param desc
	 * @param hrs
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateWebtime", method = RequestMethod.POST)
	public @ResponseBody
	String update(@RequestParam(required = false) String date, @RequestParam(required = true) String projectName,
			@RequestParam(required = true) String desc, @RequestParam(required = true) int hrs,
			@RequestParam String oper, @RequestParam String id, @ModelAttribute User user) {
		try {
			if (date == "" || projectName == "" || desc == "" || hrs == 0) {
				throw new Exception("Invalid Data. Please correct the data and proceed");
			} else {
				TimeSheetDetail sheetDetail = new TimeSheetDetail();
				sheetDetail.setDate(id);
				sheetDetail.setProjectName(projectName);
				sheetDetail.setDesc(desc);
				sheetDetail.setHrs(hrs);
				sheetDetail.setUsername(user.getUsername());
				service.update(sheetDetail);
				return "webtime";
			}
		} catch (Exception exception) {
			return "webtime";
		}
	}

	/**
	 * @param startDate
	 * @param user
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public @ResponseBody
	Boolean read(@RequestParam Date startDate, ModelMap map) throws ParseException {
		ArrayList<String> strings = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String reportDate = sdf.format(startDate);
		for (int i = 0; i < 5; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(reportDate));
			cal.add(Calendar.DATE, 1);
			reportDate = sdf.format(cal.getTime());
			String str = (day_names[cal.get(Calendar.DAY_OF_WEEK) - 1] + ' ' + month_names[cal.get(Calendar.MONTH)]
					+ ' ' + cal.get(Calendar.DATE) + ' ' + cal.get(Calendar.YEAR));
			strings.add(str);

		}
		map.addAttribute("datetime", strings);
		return true;
	}
}
