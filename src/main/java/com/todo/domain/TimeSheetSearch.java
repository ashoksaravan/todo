/**
 * 
 */
package com.todo.domain;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author VinodkumarA
 *
 */
@Document
public class TimeSheetSearch {
	/**
	 * user.
	 */
	private boolean user;
	/**
	 * from.
	 */
	private String from;
	/**
	 * to.
	 */
	private String to;
	
	/**
	 * list.
	 */
	private ArrayList<String> list;
	
	/**
	 * @return the list
	 */
	public ArrayList<String> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	/**
	 * @return the user
	 */
	public boolean isUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(boolean user) {
		this.user = user;
	}
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

}
