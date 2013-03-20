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

import com.google.gson.Gson;
import java.util.Map;
import java.util.Date; 
import java.util.List;

import formo.service.TypeService;
import formo.domain.Type;


@Controller
@RequestMapping("/type")
public class TypeController{
	
	private Gson gson;
	
	@Autowired
	private TypeService typeService;
		
	
	public TypeController(){
		System.out.println("\n\nInitializing TypeController\n\n");
		gson = new Gson();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody String getTypes(){
			
		List<Type> types = typeService.getTypes();
		String typesGson = gson.toJson(types);
		System.out.println("TypeController : " + typesGson);
		
		return typesGson;
	}

	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody String getType(@PathVariable String id){
			
		Type type = typeService.getType(Integer.parseInt(id));
		String typeGson = gson.toJson(type);
		
		return typeGson;
	}
	


	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody String saveType(@RequestBody String typeJson){
		
		System.out.println(typeJson);
			
		Type type = gson.fromJson(typeJson, Type.class);	
		typeService.saveType(type);
		
		List<Type> types = typeService.getTypes();
		String typesGson = gson.toJson(types);
	 
		return typesGson;
	}
	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public @ResponseBody String updateType(@PathVariable String id,
										      @RequestBody String typeJson){
		
		Type type = gson.fromJson(typeJson, Type.class);
		type.setId(Integer.parseInt(id));
		typeService.updateType(type);
		
		System.out.println("\n\n" + type + "\n\n");
	
		Type updatedType = typeService.getType(Integer.parseInt(id));
		String updatedTypeGson = gson.toJson(updatedType);
		
		return updatedTypeGson;
	}

	
		
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody String deleteType(@PathVariable String id){
			
		System.out.println("\n\n id : " + Integer.parseInt(id) + "\n\n");
		Type type = typeService.getType(Integer.parseInt(id));	
		System.out.println(type);
		typeService.deleteType(type);
		
		List<Type> types = typeService.getTypes();
		String typesGson = gson.toJson(types);
	 
		return typesGson;
	}	
	
	
	
}


















