package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.services.ContratServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {
	@Autowired
	ContratServiceImpl CSI;
	
	@Test
	public void getAllContratsTest(){
		assertEquals(1,CSI.getAllContrats().size());
		
		
	}
	

}
