package com.todo.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.domain.Project;
import com.todo.domain.SelectedUserProject;
import com.todo.domain.UserProject;
import com.todo.service.ProjectService;
import com.todo.service.RefDataService;

/**
 * @author vinodkumara
 *
 */
@Service
public class AddEditUserProjectCmd {

	/**
	 * dataService.
	 */
	@Autowired
	private RefDataService dataService;

	/**
	 * service.
	 */
	@Autowired
	private ProjectService service;

	/**
	 * @param data
	 * @return Boolean
	 */
	public Boolean addEditUserProject(SelectedUserProject data) {

		Map<Integer, UserProject> projectIds = new HashMap<Integer, UserProject>();
		List<UserProject> list = service.readProject(data.getUsername());
		for (UserProject userProject : list) {
			projectIds.put(userProject.getProjectId(), userProject);
		}
		for (Project project : data.getProject()) {
			if (!projectIds.containsKey(project.getProjectId())) {
				service.addUserProject(project, data.getUsername());
			} else {
				projectIds.remove(project.getProjectId());
			}
		}
		for (UserProject userProject : projectIds.values()) {
			service.deleteUserProject(userProject);
		}
		return true;

	}

}
