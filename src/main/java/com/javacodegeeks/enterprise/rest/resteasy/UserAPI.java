package com.javacodegeeks.enterprise.rest.resteasy;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Path("/")
public class UserAPI {
	static HashMap<Integer,User> userLookup= new HashMap<Integer,User>();
	static int userNumber=0;
	
	@POST
	@Path("nameStore/{name}")
	@Produces("application/json")
	public User createUser(String name) {
		User u = new User(name,UserAPI.userNumber);
		userLookup.put(UserAPI.userNumber++, u);
		return u;
	}
	
	 /* @POST
	@Path("nameStore/{name}")
	@Produces("application/json")
	public User produceJSON( @PathParam("name") String name ) {
		User u = new User(name,RESTEasyHelloWorldService.userNumber++);
		return u;
	} */
	
	/* @POST
	@Path("/Blackjack")
	@Produces("application/json")
	public String getUserName() {
		 
	} */
		
	/* @GET
	@Path("test/{pathParameter}")
	public Response responseMsg( @PathParam("pathParameter") String pathParameter,
			@DefaultValue("Nothing to say") @QueryParam("queryParameter") String queryParameter) {

		String response = "Hello from: " + pathParameter + " : " + queryParameter;

		return Response.status(200).entity(response).build(); 
	} */
}