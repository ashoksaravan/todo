/**
 * 
 */
package com.todo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author VinodkumarA
 *
 */
@Document
public class Acf2Id {
	/**
	 * id.
	 */
	@Id
	private String id;

	/**
	 * value.
	 */
	private String username;
	/**
	 * desc.
	 */
	private String acf2Id;
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
	 * @return the acf2Id
	 */
	public String getAcf2Id() {
		return acf2Id;
	}
	/**
	 * @param acf2Id the acf2Id to set
	 */
	public void setAcf2Id(String acf2Id) {
		this.acf2Id = acf2Id;
	}

}
