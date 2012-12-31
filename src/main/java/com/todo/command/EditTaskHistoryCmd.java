package com.todo.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.domain.Task;
import com.todo.domain.TaskHistory;
import com.todo.repository.TaskHistoryRepository;
import com.todo.service.TaskService;

/**
 * @author vinodkumara
 *
 */
@Service
public class EditTaskHistoryCmd{
	
	/**
	 * taskService.
	 */
	@Autowired
	private TaskService taskService;
	/**
	 * repository.
	 */
	@Autowired
	private TaskHistoryRepository repository;
	
	/**
	 * @param addEditTask
	 * @return Boolean
	 */
	public Boolean editTaskHistory(Task addEditTask) {
		String pattern = "dd/MM/yyyy";
		DateFormat format = new SimpleDateFormat(pattern);
		int len = 1;
		List<TaskHistory> list = taskService.readTaskVersion(addEditTask
				.getTaskid());
		if (list != null && list.size() > 0) {
			len = list.size() + 1;
		}
		TaskHistory history = new TaskHistory();
		history.setTaskid(addEditTask.getTaskid());
		history.setId(UUID.randomUUID().toString());
		history.setTaskname(addEditTask.getTaskname());
		history.setTaskdesc(addEditTask.getTaskdesc());
		history.setTaskstatus(addEditTask.getTaskstatus());
		history.setUsername(addEditTask.getUsername());
		history.setEditor(addEditTask.getEditor());
		history.setVersion(len);
		history.setLastUpdateDt(format.format(new Date()));
		repository.save(history);
		return true;
	}

}
