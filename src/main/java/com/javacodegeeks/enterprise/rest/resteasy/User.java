package com.javacodegeeks.enterprise.rest.resteasy;

public class User {
	private int id;
	private String name;
	private String mode;
	
	public User(String name, int id) {
	        this.name = name;
	        this.id =id;
    }
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return id;		
	}
	
}
