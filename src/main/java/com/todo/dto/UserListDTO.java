package com.todo.dto;

import java.util.List;

import com.todo.domain.User;

/**
 * @author vinodkumara
 *
 */
public class UserListDTO {

	/**
	 * users.
	 */
	private List<User> users;

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

}
