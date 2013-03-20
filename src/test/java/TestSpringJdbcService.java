import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.datasource.embedded.*;
import static junit.framework.Assert.*;
import org.junit.runner.RunWith;
import junit.framework.*;

import org.junit.Test; 

import formo.service.TypeService;
import formo.domain.Type;

import java.util.Random;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath*:/applicationContextTest.xml" })
public class TestSpringJdbcService {
	
	
	@Autowired
	TypeService typeService;
	
	
	@Test
	public void testSave(){ 
		Type type = getType("Beef Taco", 1.50, 100);
		Type savedType = typeService.saveType(type);
		assertEquals(savedType.getPrice(), type.getPrice());
	}
	
	
	@Test
	public void testUpdate(){
		Type taco = typeService.getType(0);
		taco.setPrice(1.25);
		Type updatedTaco = typeService.updateType(taco);
		assertEquals(1.25, updatedTaco.getPrice());
	}
	
	
	@Test 
	public void testSearchTacos(){
		Type pollo = getType("Pollo Taco", 2.00, 100);
		Type asada = getType("Asada Taco", 2.00, 100);
		Type fish = getType("Fish Taco", 2.75, 100);
		
		Type quacamole = getType("Guacamole", 1.25, 50);
		
		typeService.saveType(pollo);
		typeService.saveType(asada);
		typeService.saveType(fish);
		typeService.saveType(quacamole);
		
		List<Type> tacos = typeService.searchTypes("Taco");
		assertEquals(4, tacos.toArray().length);
	}
	
	
	@Test
	public void testDelete(){
		List<Type> types = typeService.searchTypes("Beef Taco");
		//assertEquals(1, types.toArray().length);
		
		Type beefTaco = types.get(0);
		typeService.deleteType(beefTaco);
		
		List<Type> typesPost = typeService.searchTypes("Beef Taco");
		//assertEquals(0, typesPost.toArray().length);
		
	}
	
	
	@Test
	public void testFindAll(){
		List<Type> types = typeService.getTypes();
		assertEquals(4, types.toArray().length);
	}



	private Type getType(String name, double price, int quantity){
		Type type = new Type();
		type.setName(name);
		type.setPrice(price);
		type.setQuantity(quantity);
		return type;
	}

	
}