package formo.service.impl;

import org.springframework.beans.factory.annotation.*;

import formo.domain.Type;
import formo.dao.TypeDao;
import formo.service.TypeService;

import java.util.List;
import java.util.ArrayList;

public class TypeServiceImpl implements TypeService{
	
	TypeDao typeDao;
	
	public TypeServiceImpl(TypeDao typeDao){
		this.typeDao = typeDao;
	}
	
	
	public Type saveType(Type type){
		Type savedType = typeDao.save(type);
		return savedType;
	}
	
	
	public Type updateType(Type type){
		Type updatedType = typeDao.update(type);
		return updatedType;
	}
	
	
	public Type deleteType(Type type){
		typeDao.delete(type.getId());
		return type;
	}
	
	
	public Type getType(int id){
		return typeDao.find(id);
	}
	
	
	public List<Type> getTypes(){
		return typeDao.findAll();
	}
	
	
	public List<Type> searchTypes(String term){
		return typeDao.search(term);
	}
	
	
}