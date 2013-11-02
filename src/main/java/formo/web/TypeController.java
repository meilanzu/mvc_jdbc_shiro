package formo.web;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import formo.dao.TypeDao;
import formo.domain.Type;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;

@Controller
@RequestMapping("/type")
public class TypeController {
	
	private Gson gson;
	
	@Autowired
	// private TypeService typeService;
	private TypeDao typeDao;
	
	private static final int RESULTS_PER_PAGE = 7;
	
	public TypeController(){
		System.out.println("\n\nInitializing TypeController\n\n");
		gson = new Gson();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody String getTypes(@RequestParam(value="offset", required = false ) String offset,
						   				 @RequestParam(value="max", required = false ) String max){
			
		List<Type> types;		
		if(offset != null) {
			int m = RESULTS_PER_PAGE;
			if(max != null){
				m = Integer.parseInt(max);
			}
			int o = Integer.parseInt(offset);
			types = typeDao.findAllOffset(m, o);	
		}else{
			types = typeDao.findAll();	
		} 
		
		int count = typeDao.count();
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("count", count);
		responseMap.put("types", types);
		
		JsonElement jsonTree = gson.toJsonTree(responseMap, Map.class);
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("results", jsonTree);
		
		// String gson = gson.toJson(responseMap);
		
		System.out.println("TypeController : " + jsonObject.toString());
		
		return jsonObject.toString();
	}


	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(HttpServletRequest request){
		List<String> roles = new ArrayList<String>();
		roles.add("admin");
		
		if (!SecurityUtils.getSubject().hasRole("admin")){
	    	System.out.println("\n\nOperation not permitted");
	      	throw new AuthorizationException("No Permission"); 
	    }	
		request.setAttribute("title", "Create New Property");
		request.setAttribute("addPropertyActive", "active");
		return "property/create";
	}



	@RequestMapping(value="/browse", method=RequestMethod.GET)
	public String browse(HttpServletRequest request){
		request.setAttribute("title", "Browse Properties");
		request.setAttribute("browsePropertyActive", "active");
		request.setAttribute("resultsPerPage", RESULTS_PER_PAGE);
		return "property/browse";
	}	
	
	

	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public String show(HttpServletRequest request, @PathVariable String id){
		request.setAttribute("title", "Show Property : " + id);
		request.setAttribute("browsePropertyActive", "active");
		
		Type type = typeDao.findById(Integer.parseInt(id));
		request.setAttribute("id", type.getId());
		request.setAttribute("name", type.getName());
		request.setAttribute("authorId", type.getAuthorId());
		request.setAttribute("raw", type.getOraw());
		return "property/show";
	}	



	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(HttpServletRequest request, 
					   @RequestParam(value="offset", required = false ) String offset,
					   @RequestParam(value="max", required = false ) String max,
					   @RequestParam(value="page", required = false ) String page){
						
		request.setAttribute("title", "List Properties");
		request.setAttribute("listPropertyActive", "active");
		request.setAttribute("resultsPerPage", RESULTS_PER_PAGE);
		if(page == null){
			System.out.println("PAGE EQUALS NULL");
			page = "1";
		}
		request.setAttribute("activePage", page);
		
		List<Type> types;
		
		if(offset != null) {
			int m = RESULTS_PER_PAGE;
			if(max != null){
				m = Integer.parseInt(max);
			}
			int o = Integer.parseInt(offset);
			types = typeDao.findAllOffset(m, o);	
		}else{
			types = typeDao.findAll();	
		} 
		
		int count = typeDao.count();
		
		request.setAttribute("types", types);
		request.setAttribute("total", count);
		
		return "property/list";
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody String getType(@PathVariable String id){
			
		Type type = typeDao.findById(Integer.parseInt(id));
		String typeGson = gson.toJson(type);
		
		return typeGson;
	}
	


	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody String saveType(@RequestBody String typeJson){
		System.out.println("SAVE TYPE");
		System.out.println(typeJson);
			
		Type type = gson.fromJson(typeJson, Type.class);	
		Type savedType = typeDao.save(type);
		
		String savedTypeGson = gson.toJson(savedType);
	 
		return savedTypeGson;
	}
	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public @ResponseBody String updateType(@PathVariable String id,
										   @RequestBody String typeJson){
		
		Type type = gson.fromJson(typeJson, Type.class);
		type.setId(Integer.parseInt(id));
		typeDao.update(type);
		
		System.out.println("\n\n" + type + "\n\n");
	
		Type updatedType = typeDao.findById(Integer.parseInt(id));
		String updatedTypeGson = gson.toJson(updatedType);
		
		return updatedTypeGson;
	}

	
		
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody String deleteType(@PathVariable String id){
			
		System.out.println("\n\n id : " + Integer.parseInt(id) + "\n\n");
		Type type = typeDao.findById(Integer.parseInt(id));	
		
		System.out.println(type);
		typeDao.delete(type.getId());
		
		List<Type> types = typeDao.findAll();
		String typesGson = gson.toJson(types);
	 
		return typesGson;
		
	}	
	
	
}


















