package formo.web;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import java.util.Map;
import java.util.Date; 
import java.util.List;



@Controller
@RequestMapping("/site")
public class SiteController{
	
	private Gson gson;
	
	
	public SiteController(){
		System.out.println("\n\nInitializing SiteController\n\n");
		gson = new Gson();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody String getSites(){
			
		
		return "{}";
	}


	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String getSites(HttpServletRequest request){
		request.setAttribute("title", "Create New Site");
		return "site/create";
	}


	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(HttpServletRequest request){
		request.setAttribute("title", "List Sites");
		request.setAttribute("sitesActive", "active");
		return "site/list";
	}

	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody String getSites(@PathVariable String id){
		
		return "{}";
	}
	


	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody String saveSites(@RequestBody String sitesJson){
		
	 	
		return "{}";
	}
	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public @ResponseBody String updateSite(@PathVariable String id,
										   @RequestBody String siteJson){
		
		
		return "{}";
	}

	
		
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody String deleteSites(@PathVariable String id){
		
		return "{}";
		
	}	
	
	
}


















