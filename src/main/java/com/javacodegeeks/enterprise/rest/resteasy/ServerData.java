package com.javacodegeeks.enterprise.rest.resteasy;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;

public class ServerData {
	private String playerNo = " ";
	
	public void update() {
		int number = 0;
		HashMap<String,User> userLookup= UserAPI.userLookup;
		
		for (Entry<String, User> entry : userLookup.entrySet())
		{
		    if(entry.getValue().getStatus().equals("loggedin")){
		    	number++;
		    }
		}
		this.playerNo = Integer.toString(number);
	}

	public String getPlayerNo() {
		return playerNo;
	}

	public void setPlayerNo(String playerNo) {
		this.playerNo = playerNo;
	}
}
