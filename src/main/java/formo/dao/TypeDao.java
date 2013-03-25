package formo.dao;

import formo.domain.Type;

import java.util.List;

public interface TypeDao{
	
	public Type findById(int id);
	
	public List<Type> findAll();
	
	public List<Type> findAllOffset(int max, int offset);
	
	public List<Type> search(String term);
	
	public Type save(Type type);
	
	public Type update(Type type);
	
	public void delete(int id);
	
	public int count();
	
}