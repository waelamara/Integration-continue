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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {

	@Autowired
	IEmployeService iEmployeService;

	@Test
	public void testAddEmployee()throws ParseException {
		Employe emp = new Employe("ben", "amara","benamara.wael@ssiiconsulting.tn", true,Role.INGENIEUR);
		Employe empAdded = iEmployeService.addOrUpdateEmploye(emp);
		assertEquals(emp.getEmail(), empAdded.getEmail());
	}

	@Test
	public void testGetNombreEmployeJPQL(){
		assertNotEquals(0,iEmployeService.getNombreEmployeJPQL());
	}

	@Test
	public  void testGetEmployePrenomById(){
		Employe emp = new Employe("Wael", "benamara","wael.ben@ssiiconsulting.tn", true,Role.INGENIEUR);
		Employe empAdded = iEmployeService.addOrUpdateEmploye(emp);
		assertEquals(empAdded.getPrenom(),iEmployeService.getEmployePrenomById(empAdded.getId()));
	}

	@Test
	public void testMettreAjourEmailByEmployeId(){
		Employe emp = new Employe("Amin", "rami","rami.ben@ssiiconsulting.tn", true,Role.CHEF_DEPARTMENT);
		Employe empAdded = iEmployeService.addOrUpdateEmploye(emp);
		Employe empUpdateted =iEmployeService.mettreAjourEmailByEmployeId("nouveau@email.test",empAdded.getId());
		assertNotEquals(empAdded.getEmail(),empUpdateted.getEmail());
	}

	@Test
	public void testGetAllEmployes(){
		assertNotEquals(0,iEmployeService.getAllEmployes().size());
	}


}
