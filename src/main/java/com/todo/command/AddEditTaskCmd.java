package com.todo.command;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.controller.SendMail;
import com.todo.domain.Task;
import com.todo.domain.User;
import com.todo.service.TaskService;
import com.todo.service.UserService;

@Service
public class AddEditTaskCmd {

	@Autowired
	private UserService service;
	@Autowired
	private TaskService taskService;

	private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

	private static String NEW = "NEW";
	private static String CANCEL = "CANCEL";
	private static String HOLD = "HOLD";
	private static String COMPLETED = "COMPLETED";

	public Task addEditTask(Task addEditTask) {
		String subject = new String();
		String desc = new String();
		Task task = new Task();
		task.setTaskid(addEditTask.getTaskid());
		task.setTaskname(addEditTask.getTaskname());
		task.setTaskdesc(addEditTask.getTaskdesc());
		task.setPriority(addEditTask.getPriority());
		task.setTaskstatus(addEditTask.getTaskstatus());
		task.setUsername(addEditTask.getUsername());
		task.setCreateduser(addEditTask.getCreateduser());
		task.setEditor(addEditTask.getEditor());
		if (addEditTask.getCclist() != null
				&& addEditTask.getCclist().length() > 0) {
			task.setCclist(addEditTask.getCclist());
		}
		Task newTask = taskService.addEditTask(task);
		User user = new User();
		user.setUsername(newTask.getUsername());
		user = service.read(user);
		if (user != null) {
			ArrayList<String> toArrList = new ArrayList<String>();
			toArrList.add(user.getMailId());
			map.put("TO", toArrList);
		}
		User author = new User();
		author.setUsername(newTask.getCreateduser());
		author = service.read(author);
		ArrayList<String> ccArrList = new ArrayList<String>();
		if (author != null) {
			ccArrList.add(author.getMailId());
		}
		if (newTask.getCclist() != null && newTask.getCclist().length() > 0) {
			String[] strings = newTask.getCclist().split(",");
			for (String string : strings) {
				User ccUser = new User();
				ccUser.setUsername(string);
				ccUser = service.read(ccUser);
				ccArrList.add(ccUser.getMailId());
			}
			map.put("CC", ccArrList);
		}
		subject = newTask.getTaskname() + "\t\t Assigned to: "
				+ newTask.getUsername() + "\t\t Status: "
				+ newTask.getTaskstatus();
		if (newTask.getTaskstatus().equals(COMPLETED)) {
			desc = "The Task has been completed.";
		} else if (newTask.getTaskstatus().equals(HOLD)) {
			desc = "The Task is hold.";
		} else if (newTask.getTaskstatus().equals(CANCEL)) {
			desc = "The Task has been cancelled.";
		} else if (newTask.getTaskstatus().equals(NEW)) {
			desc = "A New Task has been assigned.";
		} else {
			desc = "A Task has been assigned for development.";
		}
		SendMail sms = new SendMail();
		sms.sendMail(
				desc + "\n\n" + "Task Description : " + newTask.getTaskdesc(),
				map, subject);
		return newTask;

	}

}
