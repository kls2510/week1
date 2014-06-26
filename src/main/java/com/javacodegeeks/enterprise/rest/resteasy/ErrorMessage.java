package com.javacodegeeks.enterprise.rest.resteasy;

public class ErrorMessage {
	String errMessage;
	
	public ErrorMessage(String m){
		this.errMessage = m;
	}
	
	public String getErrMessage() {
		return this.errMessage;
	}
}
