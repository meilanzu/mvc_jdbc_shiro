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

import formo.dao.TypeDao;
import formo.domain.Type;


@Controller
@RequestMapping("/type")
public class TypeController{
	
	private Gson gson;
	
	@Autowired
	// private TypeService typeService;
	private TypeDao typeDao;
		
	
	public TypeController(){
		System.out.println("\n\nInitializing TypeController\n\n");
		gson = new Gson();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody String getTypes(){
			
		List<Type> types = typeDao.findAll();
		String typesGson = gson.toJson(types);
		System.out.println("TypeController : " + typesGson);
		
		return typesGson;
	}


	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(HttpServletRequest request){
		request.setAttribute("title", "Create New Property");
		request.setAttribute("addPropertyActive", "active");
		return "property/create";
	}



	@RequestMapping(value="/browse", method=RequestMethod.GET)
	public String browse(HttpServletRequest request){
		request.setAttribute("title", "Browse Properties");
		request.setAttribute("browsePropertyActive", "active");
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
		request.setAttribute("raw", type.getRaw());
		return "property/show";
	}	



	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(HttpServletRequest request, 
					   @RequestParam(value="offset", required = false ) String offset,
					   @RequestParam(value="max", required = false ) String max){
						
		request.setAttribute("title", "List Properties");
		request.setAttribute("browsePropertyActive", "active");
		
		List<Type> types;
		
		if(offset != null && max != null) {
			int m = Integer.parseInt(max);
			int o = Integer.parseInt(offset);
			types = typeDao.findAllOffset(o, m);	
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


















