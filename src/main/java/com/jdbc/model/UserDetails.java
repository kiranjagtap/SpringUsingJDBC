package com.jdbc.model;

public class UserDetails {

	int id;
	String name;

	public UserDetails(int userId, String name) {
		this.id = id;
		this.name = name;
	}

	public UserDetails() {

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserDetails{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
