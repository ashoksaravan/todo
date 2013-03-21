/**
 * 
 */
package com.todo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vinodkumara
 *
 */
@Document
public class TimeSheetDetail {
	
	/**
	 * id.
	 */
	@Id
	private String id;
	/**
	 * username.
	 */
	private String username;
	/**
	 * date.
	 */
	private String date;
	/**
	 * projectName.
	 */
	private String projectName;
	/**
	 * desc.
	 */
	private String desc;
	/**
	 * hrs.
	 */
	private int hrs;	
	/**
	 * buffer.
	 */
	private String buffer;
	/**
	 * @return the buffer
	 */
	public String getBuffer() {
		return buffer;
	}
	/**
	 * @param buffer the buffer to set
	 */
	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the hrs
	 */
	public int getHrs() {
		return hrs;
	}
	/**
	 * @param hrs the hrs to set
	 */
	public void setHrs(int hrs) {
		this.hrs = hrs;
	}
	

}
