package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.EntrepriseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
	@Autowired
	EntrepriseServiceImpl entrepriseServiceImpl;
	@Test
	public void testAjouterEntreprise()throws ParseException {
		Entreprise e = new Entreprise("Ste Lassoued", "informatique");
		int id =entrepriseServiceImpl.ajouterEntreprise(e) ;
		assertEquals(e.getId(), id);
	} 
	@Test
	public void testGetAllDepartementsNamesByEntreprise()throws ParseException  {
		List<String> listNamesEntreprise = entrepriseServiceImpl.getAllDepartementsNamesByEntreprise(1);
		// if there are 5 users in DB : 
		assertEquals(1, listNamesEntreprise.size());
	}
	@Test
	public void testGetEntrepriseById()throws ParseException  {
		Entreprise e = entrepriseServiceImpl.getEntrepriseById(1);
		assertEquals(1L, e.getId());
	}
	
}
