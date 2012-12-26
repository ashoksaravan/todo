/**
 * 
 */
package com.todo.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.todo.domain.User;

/**
 * @author vinodkumara
 *
 */
@Service
public class SearchUserCmd {
	
	/**
	 * mongoTemplate.
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<User> searchUsers(String searchField, String searchOper, String searchString){
		Criteria criteria = null;
		if (searchField != null && searchField.trim().length() > 0 && searchOper != null
				&& searchOper.trim().length() > 0 && searchString != null
				&& searchString.trim().length() > 0) {
			if(searchOper.equals("eq")){
				criteria = Criteria.where(searchField).is(searchString);
			}else if(searchOper.equals("cn")){
				criteria = Criteria.where(searchField).regex(searchString);
			}
			List<User> list = mongoTemplate.find(new Query(criteria), User.class);
			return list;
		}
		
		return null;
		
	}
}
