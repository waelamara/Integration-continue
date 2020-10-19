package tn.esprit.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {

	@Autowired
	IEmployeService iEmployeService;

	@Test
	public void testAddEmployee()throws ParseException {
		Employe emp = new Employe("Mayssa", "Mayssa1","Mayssa1.may@ssiiconsulting.tn", true,Role.INGENIEUR);
		Employe empAdded = iEmployeService.addOrUpdateEmploye(emp);
		assertEquals(emp.getEmail(), empAdded.getEmail());
	}
	
}
