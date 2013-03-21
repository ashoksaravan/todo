/**
 * 
 */
package com.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.domain.TimeSheetDetail;

/**
 * @author vinodkumara
 *
 */
public interface TimeSheetDetailRepository extends MongoRepository<TimeSheetDetail, String>{
	/**
	 * @param input
	 * @return TimeSheetDetail
	 */
	TimeSheetDetail findByProjectName(String input);
	
	/**
	 * @param date
	 * @return
	 */
	TimeSheetDetail findByDate(String date);

}
