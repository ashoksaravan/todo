package com.todo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.todo.domain.Task;
import com.todo.domain.TaskHistory;
import com.todo.repository.TaskHistoryRepository;
import com.todo.repository.TaskRepository;

@Service
public class TaskService {

	/**
	 * taskRepository.
	 */
	@Autowired
	private TaskRepository taskRepository;

	/**
	 * taskHistoryRepository.
	 */
	@Autowired
	private TaskHistoryRepository taskHistoryRepository;

	/**
	 * mongoTemplate.
	 */
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * NEW.
	 */
	private static String NEW = "NEW";
	/**
	 * HOLD.
	 */
	private static String HOLD = "HOLD";
	/**
	 * DEV.
	 */
	private static String DEV = "DEV";

	/**
	 * @param username
	 * @return
	 */
	public List<Task> readAll(String username) {
		return taskRepository.findTaskByUsername(username);
	}

	/**
	 * @param task
	 * @return
	 */
	public Task addEditTask(Task task) {
		Task existingTask = taskRepository.findTaskByTaskid(task.getTaskid());
		if (existingTask == null) {
			task.setTaskid(UUID.randomUUID().toString());
			taskRepository.save(task);
			return task;
		} else {
			existingTask.setTaskid(task.getTaskid());
			existingTask.setTaskname(task.getTaskname());
			existingTask.setTaskdesc(task.getTaskdesc());
			existingTask.setPriority(task.getPriority());
			existingTask.setTaskstatus(task.getTaskstatus());
			existingTask.setUsername(task.getUsername());
			existingTask.setCreateduser(task.getCreateduser());
			existingTask.setCclist(task.getCclist());
			taskRepository.save(existingTask);
		}
		return existingTask;
	}

	/**
	 * @param taskid
	 * @return
	 */
	public List<TaskHistory> readTaskVersion(String taskid) {
		return taskHistoryRepository.findTaskByTaskid(taskid);
	}

	/**
	 * @param task
	 * @return
	 */
	public List<Task> search(Task task) {
		Criteria criteria = null;
		if (task.getProjectId() != null && task.getProjectId().intValue() > 0) {
			criteria = Criteria.where("projectId").is(task.getProjectId());
		}
		if (task.getTaskname() != null && task.getTaskname().trim().length() > 0) {
			criteria = criteria.and("taskname").is(task.getTaskname());
		} else {
			if (task.getTaskname() != null && task.getTaskname().trim().length() > 0) {
				criteria = Criteria.where("taskname").is(task.getTaskname());
			}
		}
		if (task.getPriority() != null && task.getPriority().trim().length() > 0 && criteria != null) {
			criteria = criteria.and("priority").is(task.getPriority());
		} else {
			if (task.getPriority() != null && task.getPriority().trim().length() > 0) {
				criteria = Criteria.where("priority").is(task.getPriority());
			}
		}
		if (task.getTaskstatus() != null && task.getTaskstatus().trim().length() > 0 && criteria != null) {
			criteria = criteria.and("taskstatus").is(task.getTaskstatus());
		} else {
			if (task.getTaskstatus() != null && task.getTaskstatus().trim().length() > 0) {
				criteria = Criteria.where("taskstatus").is(task.getTaskstatus());
			}
		}
		if (task.getUsername() != null && task.getUsername().trim().length() > 0 && criteria != null) {
			criteria = criteria.and("username").is(task.getUsername());
		} else {
			if (task.getUsername() != null && task.getUsername().trim().length() > 0) {
				criteria = Criteria.where("username").is(task.getUsername());
			}
		}
		List<Task> list = mongoTemplate.find(new Query(criteria), Task.class);
		return list;
	}

	/**
	 * @param task
	 * @return
	 */
	public List<Task> loadTask(Task task) {
		Criteria criteria = null;
		if (task.getUsername() != null && task.getUsername().trim().length() > 0 && task.getProjectId() != null
				&& task.getProjectId().intValue() > 0) {
			criteria = Criteria.where("username").is(task.getUsername()).and("projectId").is(task.getProjectId())
					.and("taskstatus").in(NEW, DEV, HOLD);
			List<Task> list = mongoTemplate.find(new Query(criteria), Task.class);
			return list;
		}
		return null;

	}
}
