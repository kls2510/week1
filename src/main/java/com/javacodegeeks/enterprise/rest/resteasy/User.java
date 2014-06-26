package com.javacodegeeks.enterprise.rest.resteasy;

public class User {
	private String name;
	private String mode;
	private String password;
	private String status;
	
	public User(String name, String pw) {
	        this.name = name;
	        this.password = pw;
	        this.status = "loggedout";
    }
	
	public void login() {
		this.status = "loggedin";
	}
	
	public void logout() {
		this.status = "loggedout";
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}	
}
