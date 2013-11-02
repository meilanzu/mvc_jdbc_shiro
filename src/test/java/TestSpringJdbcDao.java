import formo.dao.TypeDao;
import formo.domain.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath*:/applicationContextTest.xml" })
public class TestSpringJdbcDao {
	
	@Autowired
	TypeDao typeDao;
	
	private String[] typeNames = {"//Lat", "//Lon", "//Min", "//Max", "//Error"};
	
	
	@Test
	public void testSavedTypes(){ 
		saveTypes();
		System.out.println("test type dao");
		List<Type> types = typeDao.findAll();
		assertTrue(types.toArray().length == typeNames.length);
	}
	
	
	@Test
	public void testDeleteFirst(){
		Type type = typeDao.findById(0);
		assertEquals(type.getName(), typeNames[0]);
		typeDao.delete(0);
		
		List<Type> types = typeDao.findAll();	
		assertEquals(types.toArray().length, typeNames.length - 1 );
	}
	
	
	@Test
	public void testSearch(){
		List<Type> types = typeDao.search("o");
		assertEquals(2, types.toArray().length);
	}
	
	
	@Test
	public void testUpdateTaco(){
		List<Type> types = typeDao.search("on");
		assertEquals(1, types.toArray().length);
		
		Type pre = types.get(0);
		int id = pre.getId();
		pre.setName("//newtype");
		typeDao.update(pre);
		
		Type type = typeDao.findById(id);
		assertEquals("//newtype", type.getName());
	}
	
	
	@Test
	public void testDeleteRemaining(){
		List<Type> remainingTypes = typeDao.findAll();
		assertEquals(4, remainingTypes.toArray().length);
		
		Iterator<Type> typeIterator = remainingTypes.iterator();
		while (typeIterator.hasNext()) {
			Type type = (Type)typeIterator.next();
			typeDao.delete(type.getId());
		}
		
		List<Type> types = typeDao.findAll();
		assertEquals(0, types.toArray().length);
	}
	
	
	private void saveTypes(){
		Random random = new Random();
		for(int f = 0; f < typeNames.length; f++){
			Type type = new Type();
			type.setId(f);
			type.setName(typeNames[f]);
			type.setAuthorId(random.nextInt());
			type.setOraw("{ name : \" " + typeNames[f] + "\"}");
			typeDao.save(type);
		}
	}
	
	
}