package formo.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Value;  

import java.util.List;
import java.util.ArrayList;

import formo.dao.TypeDao;
import formo.domain.Type;


public class TypeDaoImpl extends JdbcDaoSupport implements TypeDao{
	
	@Value("${find.sql}")
	private String findSql;
	
	@Value("${find.all.sql}")
	private String findAllSql;
	
	@Value("${search.sql}")
	private String searchSql;
	
	@Value("${save.sql}")
	private String save;

	@Value("${count.sql}")
	private String countSql;
	
	@Value("${update.sql}")
	private String updateSql;
	
	@Value("${delete.sql}")
	private String deleteSql;
	
	private static final String TERM = "{{TERM}}";

	public Type find(int id) {
		try{
			
			Type type = (Type) getJdbcTemplate().queryForObject(findSql, new Object[] { id }, 
				new BeanPropertyRowMapper(Type.class));
			
			return type;
			
		}catch (Exception e){
			e.printStackTrace();
		}	
		return null;	
	}
	
	
	public List<Type> findAll() {
		try{
			List<Type> type = getJdbcTemplate().query(findAllSql, new BeanPropertyRowMapper(Type.class));
			
			return type;
		
		}catch (Exception e){
			e.printStackTrace();
		}	
		return null;	
	}
	
	
	public List<Type> search(String term){
		try{
			
			String search = searchSql.replace(TERM, term);
			List<Type> types = getJdbcTemplate().query(search, new BeanPropertyRowMapper(Type.class));
			return types;
			
		}catch ( Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public Type save(Type type){

		int id = getJdbcTemplate().queryForInt(countSql, new Object[0]);
		
		getJdbcTemplate().update(insertSql, new Object[] { 
			id, type.getName(), type.getPrice(), type.getQuantity()  
		});
		
		Type savedType = find(id);
		
		return savedType;
		
	};
	
	
	public Type update(Type type){

		getJdbcTemplate().update(updateSql, new Object[] { 
			type.getName(), type.getPrice(), type.getQuantity(), type.getId()  
		});
		
		return find(type.getId());
				
	};
	
	
	public void delete(int id) {
		getJdbcTemplate().update(deleteSql, new Object[] {id });
	};

	
}