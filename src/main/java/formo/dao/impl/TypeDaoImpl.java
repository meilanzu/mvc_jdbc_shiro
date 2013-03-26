package formo.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Value;  

import java.util.List;
import java.util.ArrayList;

import formo.dao.TypeDao;
import formo.domain.Type;


public class TypeDaoImpl extends JdbcDaoSupport implements TypeDao{
	
	@Value("${find.by.id.sql}")
	private String findById;
	
	@Value("${find.all.sql}")
	private String findAllSql;
	
	@Value("${find.paginate}")
	private String paginate;
	
	@Value("${search.sql}")
	private String searchSql;
	
	@Value("${save.sql}")
	private String saveSql;

	@Value("${count.sql}")
	private String countSql;
	
	@Value("${update.sql}")
	private String updateSql;
	
	@Value("${delete.sql}")
	private String deleteSql;
	
	private static final String TERM   = "{{TERM}}";
	private static final String OFFSET = "{{OFFSET}}";
	private static final String MAX    = "{{MAX}}";

	public Type findById(int id) {
		try{
			
			Type type = (Type) getJdbcTemplate().queryForObject(findById, new Object[] { id }, 
				new BeanPropertyRowMapper(Type.class));
			
			return type;
			
		}catch (Exception e){
			e.printStackTrace();
		}	
		return null;	
	}
	

	public List<Type> findAll() {
		try{

			System.out.println("find all " + findAllSql);
			List<Type> types = getJdbcTemplate().query(findAllSql, new BeanPropertyRowMapper(Type.class));
			
			return types;
		
		}catch (Exception e){
			e.printStackTrace();
		}	
		return null;	
	}
	
		
	public List<Type> findAllOffset(int max, int offset) {
		try{
			
			String sql = findAllSql;
			sql+= " " + paginate;
			sql = sql.replace(MAX, Integer.toString(max));
			sql = sql.replace(OFFSET, Integer.toString(offset));
			
			
			System.out.println("find all " + sql);
			List<Type> types = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Type.class));
			
			return types;
		
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
		
		System.out.println("save type " + countSql + "  " + id);
		System.out.println("type name" + type.getName());
		System.out.println("type authorId" + type.getAuthorId());
		System.out.println("type raw" + type.getRaw());
		
		getJdbcTemplate().update(saveSql, new Object[] { 
			id, type.getName(), type.getAuthorId(), type.getRaw()  
		});
		
		Type savedType = findById(id);
		
		System.out.println("savedType " + savedType.toString());
		return savedType;
		
	};
	
	
	public int count(){
		int id = getJdbcTemplate().queryForInt(countSql, new Object[0]);
		System.out.println("count types " + countSql + "  " + id);
	 	return id; 
	}
	
	
	public Type update(Type type){

		getJdbcTemplate().update(updateSql, new Object[] { 
			type.getName(), type.getAuthorId(), type.getRaw(), type.getId()  
		});
		
		return findById(type.getId());
				
	};
	
	
	public void delete(int id) {
		getJdbcTemplate().update(deleteSql, new Object[] {id });
	};

	
}