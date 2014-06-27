package com.javacodegeeks.enterprise.rest.resteasy;

public class User {
	private String name;
	private String mode;
	private String password;
	private String status;
	private int wins=0;
	private int losses=0;
	private int draws=0;
	
	public void addStat(String outcome){
		if(outcome.equals("win")){
			this.wins++;
		}
		else if(outcome.equals("loss")){
			this.losses++;
		}
		else{
			this.draws++;
		}
	}
	
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
	
	public int getWins() {
		return this.wins;
	}
	public int getLosses() {
		return this.losses;
	}
	public int getDraws() {
		return this.draws;
	}
}
