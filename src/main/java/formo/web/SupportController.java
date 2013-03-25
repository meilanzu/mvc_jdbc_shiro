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
@RequestMapping("/support")
public class SupportController{
	
	private Gson gson;
	
	
	public SupportController(){
		System.out.println("\n\nInitializing SupportController\n\n");
		gson = new Gson();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(HttpServletRequest request){
			
		request.setAttribute("title", "Support");
		request.setAttribute("supportActive", "active");
		return "support/index";
	}


	
}


















