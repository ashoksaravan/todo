/**
 * 
 */
package com.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.todo.domain.TimeSheetDetail;
import com.todo.domain.UserProject;
import com.todo.domain.WebProject;
import com.todo.repository.TimeSheetDetailRepository;
import com.todo.repository.WebProjectRepository;

/**
 * @author vinodkumara
 * 
 */
@Service
public class WebtimeService {
	/**
	 * projectRepository.
	 */
	@Autowired
	private WebProjectRepository projectRepository;

	/**
	 * projectRepository.
	 */
	@Autowired
	private TimeSheetDetailRepository sheetDetailRepository;

	/**
	 * mongoTemplate.
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * @return List<WebProject>
	 */
	public List<WebProject> readProject() {
		return projectRepository.findAll();
	}

	/**
	 * @param project
	 * @return Project
	 */
	public WebProject add(WebProject project) {
		project.setId(UUID.randomUUID().toString());
		return projectRepository.save(project);

	}

	/**
	 * @param project
	 * @param oldValue
	 * @return WebProject
	 */
	public WebProject edit(WebProject project, String oldValue) {
		WebProject editProject = projectRepository.findByProjectName(oldValue);
		if (editProject == null) {
			return null;
		}
		editProject.setProjectName(project.getProjectName());
		return projectRepository.save(editProject);
	}

	/**
	 * @return List<TimeSheetDetail>
	 */
	public List<TimeSheetDetail> readAll() {
		return sheetDetailRepository.findAll();
	}

	/**
	 * @param sheetDetail
	 * @return
	 */
	public TimeSheetDetail update(TimeSheetDetail sheetDetail) {
		List<TimeSheetDetail> timeSheetDetail = query(sheetDetail.getDate(), sheetDetail.getUsername());
		if (timeSheetDetail.size() == 0 || timeSheetDetail == null) {
			return sheetDetailRepository.save(sheetDetail);
		}
		for (TimeSheetDetail detail : timeSheetDetail) {
			detail.setDesc(sheetDetail.getDesc());
			detail.setHrs(sheetDetail.getHrs());
			detail.setProjectName(sheetDetail.getProjectName());
			return sheetDetailRepository.save(detail);
		}
		return null;
	}

	/**
	 * @param list1
	 * @param users
	 * @param username
	 * @param projectId 
	 * @return List<TimeSheetDetail>
	 */
	public List<TimeSheetDetail> queryWebTime(ArrayList<String> list1, boolean users, String username, Integer projectId) {
		Criteria criteria = null;
		Criteria criteria1 = null;
		ArrayList<String> names = new ArrayList<String>();
		List<TimeSheetDetail> list = new ArrayList<TimeSheetDetail>();
		if(null != projectId  && projectId != 0){
			criteria1 = Criteria.where("projectId").is(projectId);
			List<UserProject> userProjects = mongoTemplate.find(new Query(criteria1), UserProject.class);
			for (UserProject userProject : userProjects) {
				names.add(userProject.getUsername());
			}
		}
		if (list1 != null) {
			if (!users) {
				criteria = Criteria.where("username").is(username);
				criteria = criteria.and("date").in(list1);
			} else {
				if (null != projectId  && projectId != 0 && null != names && names.size() >= 0) {
					criteria = Criteria.where("username").in(names).and("date").in(list1);
				} else {
					criteria = Criteria.where("date").in(list1);
				}
			}
			list = mongoTemplate.find(new Query(criteria), TimeSheetDetail.class);
			return list;
		}
		return null;
	}

	/**
	 * @param date
	 * @param user
	 * @return
	 */
	public List<TimeSheetDetail> query(String date, String user) {
		Criteria criteria = null;
		if (date != null) {
			criteria = Criteria.where("date").is(date).and("username").is(user);
			List<TimeSheetDetail> list = mongoTemplate.find(new Query(criteria), TimeSheetDetail.class);
			return list;
		}
		return null;
	}
}
