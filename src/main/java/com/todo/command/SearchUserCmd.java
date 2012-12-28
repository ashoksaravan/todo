/**
 * 
 */
package com.todo.command;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.todo.domain.Role;
import com.todo.domain.User;
import com.todo.repository.RoleRepository;

/**
 * @author vinodkumara
 * 
 */
@Service
public class SearchUserCmd {

	/**
	 * role.
	 */
	private static String role = "role";

	/**
	 * roleRepository.
	 */
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * mongoTemplate.
	 */
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<User> searchUsers(String searchField, String searchOper, String searchString) {
		Criteria criteria = null;
		Role existingRole = new Role();
		List<User> list = new ArrayList<User>();
		if (searchField != null && searchField.trim().length() > 0 && searchOper != null
				&& searchOper.trim().length() > 0 && searchString != null && searchString.trim().length() > 0) {
			if (role.equals(searchField)) {
				if (searchOper.equals("eq")) {
					existingRole = roleRepository.findByDesc(searchString);
				} else if (searchOper.equals("cn")) {
					existingRole = roleRepository.findByDescLike(searchString);
				}
				if (existingRole != null) {
					searchField = "role.$id";
					criteria = Criteria.where(searchField).is(existingRole.getId().toString());
					list = mongoTemplate.find(new Query(criteria), User.class);
				}
			} else {
				if (searchOper.equals("eq")) {
					criteria = Criteria.where(searchField).is(searchString);
				} else if (searchOper.equals("cn")) {
					criteria = Criteria.where(searchField).regex(searchString);
				}
				list = mongoTemplate.find(new Query(criteria), User.class);
			}
		}
		return list;
	}
}
