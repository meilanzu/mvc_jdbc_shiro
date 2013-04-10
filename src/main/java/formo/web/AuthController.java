package formo.web;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

import java.util.Map;
import java.util.HashMap;
import java.util.Date; 
import java.util.List;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.AuthenticationException;


@Controller
@RequestMapping("/auth")
public class AuthController{
	
	private Gson gson;
	
	public AuthController(){
		System.out.println("\n\nInitializing AuthController\n\n");
		gson = new Gson();
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public @ResponseBody String login(){
		
		JsonElement jsonTree;
		
		JsonObject jsonObject = new JsonObject();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		try{
			
			String username = "root";
			String password = "secret";

			UsernamePasswordToken token = new UsernamePasswordToken( username, password );
			token.setRememberMe(true);

			//With most of Shiro, you'll always want to make sure you're working with the currently executing user, referred to as the subject
			Subject currentUser = SecurityUtils.getSubject();

			//Authenticate the subject by passing
			//the user name and password token
			//into the login method
			currentUser.login(token);

			System.out.println("\n\n << AUTHENTICATED >> \n\n");

			responseMap.put("currentUser", currentUser.getPrincipal());

			jsonTree = gson.toJsonTree(responseMap, Map.class);
			jsonObject.add("results", jsonTree);

			
		} catch ( UnknownAccountException uae ) { 
			uae.printStackTrace();
		} catch ( IncorrectCredentialsException ice ) {
			ice.printStackTrace();
		} catch ( LockedAccountException lae ) { 
			lae.printStackTrace();
		} catch ( ExcessiveAttemptsException eae ) { 
			eae.printStackTrace();
		} catch ( AuthenticationException ae ) {
			ae.printStackTrace();
		}

		return jsonObject.toString();
	}	
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public @ResponseBody String logout(HttpServletRequest request){
		
		Subject currentUser = SecurityUtils.getSubject();

		//Authenticate the subject by passing
		//the user name and password token
		//into the login method
		currentUser.logout();		
		
		return "logged out";
	}
	
}


















