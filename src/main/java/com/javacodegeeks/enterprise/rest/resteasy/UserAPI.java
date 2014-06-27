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
import com.javacodegeeks.enterprise.rest.resteasy.Blackjack;
import com.javacodegeeks.enterprise.rest.resteasy.GameSpace;

@Path("/")
public class UserAPI {
	static HashMap<String,User> userLookup= new HashMap<String,User>();
	static HashMap<String,GameSpace> gameLookup = new HashMap<String,GameSpace>();
	static ServerData serverData = new ServerData();
	
	@POST
	@Path("playBlackjack/stick")
	@Produces("application/json")
	public Response stick(String name) {
		int length = name.length();
		int nameStart = 9;
		String nameS = "";
		char[] ca = name.toCharArray();
		for(int i = nameStart; i<length; i++){
			nameS += ca[i];
		}
		for (Entry<String, GameSpace> entry : gameLookup.entrySet())
		{
		    if(name.equals(entry.getKey())){
		    	GameSpace g = entry.getValue();
		    	g.makeOutcome();
		    	String outcome = g.getOutcomeSingle();
		    	gameLookup.remove(name);
		    	User u = userLookup.get(nameS);
		    	u.addStat(outcome);
		    	return Response.status(200).entity(g).build();
		    }
		}
		ErrorMessage e = new ErrorMessage("Your game no longer exists");
    	return Response.status(200).entity(e).build();
	}
	
	@POST
	@Path("playBlackjack/stats")
	@Produces("application/json")
	public Response stats(String name) {
		int length = name.length();
		int nameStart = 9;
		String nameS = "";
		char[] ca = name.toCharArray();
		for(int i = nameStart; i<length; i++){
			nameS += ca[i];
		}
		for (Entry<String, User> entry : userLookup.entrySet())
		{
		    if(nameS.equals(entry.getKey())){
		    	return Response.status(200).entity(entry.getValue()).build();
		    }
		}
		ErrorMessage e = new ErrorMessage("This account does not exist");
    	return Response.status(200).entity(e).build();
	}
	
	@POST
	@Path("playBlackjack/logout")
	@Produces("application/json")
	public void logout(String name) {
		int length = name.length();
		int nameStart = 9;
		String nameS = "";
		char[] ca = name.toCharArray();
		for(int i = nameStart; i<length; i++){
			nameS += ca[i];
		}
		for (Entry<String, User> entry : userLookup.entrySet())
		{
		    if(nameS.equals(entry.getKey())){
		    	User u = entry.getValue();
		    	u.logout();
		    }
		}
	}
	
	@POST
	@Path("playBlackjack")
	@Produces("application/json")
	public Response playBlackjack(String name) {
		for (Entry<String, GameSpace> entry : gameLookup.entrySet())
		{
		    if(name.equals(entry.getKey())){
		    	ErrorMessage e = new ErrorMessage("You are already connected to a game");
		    	return Response.status(200).entity(e).build();
		    }
		}
		GameSpace g = Blackjack.init(name);
		gameLookup.put(name, g);	
		return Response.status(200).entity(g).build();
	}
	
	@POST
	@Path("playBlackjack/hit")
	@Produces("application/json")
	public Response blackjackHit(String name) {
		GameSpace game = gameLookup.get(name);
		game.addCard();
		return Response.status(200).entity(game).build();
	}
	
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