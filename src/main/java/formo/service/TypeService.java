package formo.service;

import formo.domain.Type;
import formo.dao.TypeDao;

import java.util.List;

public interface TypeService {
	
	public Type saveType(Type type);
	
	public Type updateType(Type type);
	
	public Type deleteType(Type type);
	
	public Type getType(int id);
	
	public List<Type> getTypes();
	
	public List<Type> searchTypes(String term);
	
}