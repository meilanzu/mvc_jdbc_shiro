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
@RequestMapping("/tag")
public class TagController{
	
	private Gson gson;
	
	
	public TagController(){
		System.out.println("\n\nInitializing TagController\n\n");
		gson = new Gson();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody String getTags(){
			
		
		return "{}";
	}


	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String getTags(HttpServletRequest request){
		request.setAttribute("title", "Create New Tag");
		return "tag/create";
	}



	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(HttpServletRequest request){
		request.setAttribute("title", "List Tags");
		request.setAttribute("tagsActive", "active");
		return "tag/list";
	}


	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody String getTags(@PathVariable String id){
		
		return "{}";
	}
	


	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody String saveTags(@RequestBody String tagsJson){
		
	 	
		return "{}";
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public @ResponseBody String updateTag(@PathVariable String id,
										   @RequestBody String tagJson){
		
		
		return "{}";
	}

	
		
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody String deleteTags(@PathVariable String id){
		
		return "{}";
	}	
	
	
}


















