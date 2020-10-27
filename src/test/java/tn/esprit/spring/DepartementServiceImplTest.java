package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.services.DepartementServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementServiceImplTest {
	@Autowired
	DepartementServiceImpl DSI;
	
	@Test
	public void getAllDepartements(){
		assertEquals(1,DSI.getAllDepartements().size());
		
		
	}

}
