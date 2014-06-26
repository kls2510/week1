package com.javacodegeeks.enterprise.rest.resteasy;

import java.util.HashMap;
import java.util.Map.Entry;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Path("/")
public class UserAPI {
	static HashMap<String,User> userLookup= new HashMap<String,User>();
	static ServerData serverData = new ServerData();
	
	@POST
	@Path("userno")
	@Produces("application/json")
	public Response getUserNo(){
		serverData.update();
		return Response.status(200).entity(UserAPI.serverData).build();
	}
	
	@POST
	@Path("nameStore/{data}")
	@Produces("application/json")
	public Response createUser(String data){
		int length = data.length();
		int nameend = 0;
		String name = "";
		String password = "";
		char[] ca = data.toCharArray();
		for(int i = 5; ca[i] != '&'; i++){
			name += ca[i];
			nameend=i;
		}
		for(int j = nameend+11; j<length; j++){
			password += ca[j];
		}
		for (Entry<String, User> entry : userLookup.entrySet())
		{
		    if(name.equals(entry.getKey())){
		    	return Response.status(500).build();
		    }
		}
		User u = new User(name, password);
		userLookup.put(name, u);
		return Response.status(200).entity(u).build();			
	}
	
	@POST
	@Path("nameStore/login/{data}")
	@Produces("application/json")
	public Response userLogin(String data){
		int length = data.length();
		int nameend = 0;
		String name = "";
		String password = "";
		char[] ca = data.toCharArray();
		for(int i = 5; ca[i] != '&'; i++){
			name += ca[i];
			nameend=i;
		}
		for(int j = nameend+11; j<length; j++){
			password += ca[j];
		}
		for (Entry<String, User> entry : userLookup.entrySet())
		{
		    if(name.equals(entry.getKey())){
		    	User u = entry.getValue();
		    	if(u.getPassword().equals(password)){
			    	if(u.getStatus().equals("loggedin")){
			    		ErrorMessage f = new ErrorMessage("Account is already logged in");
			    		return Response.status(200).entity(f).build();
			    	}
			    	else {
			    		u.login();
			    		return Response.status(200).entity(u).build();
			    	}
		    	}
			    else {
			    	ErrorMessage f = new ErrorMessage("wrong password");
			    	return Response.status(200).entity(f).build();
			    }
		    }
		}
		ErrorMessage g = new ErrorMessage("This account does not exist");
	    return Response.status(200).entity(g).build();
	}
	
	@GET
	public String callService(@CookieParam("sessionid") String sessionid) {
		return "a";
	 }

}