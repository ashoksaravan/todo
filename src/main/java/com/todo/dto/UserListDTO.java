package com.todo.dto;

import java.util.List;

import com.todo.domain.User;

public class UserListDTO {

	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
