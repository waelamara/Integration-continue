package tn.esprit.spring;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.DepartementServiceImpl;
import tn.esprit.spring.services.TimesheetServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceImplTest {
	
	@Autowired
	private TimesheetServiceImpl TimesheetServiceImpl;
	@Autowired
	private DepartementServiceImpl departementServiceImpl;
	@Autowired
	private MissionRepository  missionRepository; 
	@Autowired
	DepartementRepository departementRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	@Test
	public void ajouterMission() {
		Mission	mission=new Mission("nou","nou");
		TimesheetServiceImpl.ajouterMission(mission);
	}
	
	@Test
	public void affecterMissionADepartement(){
	Mission	m1=missionRepository.findById(1).get();
	Departement d1=departementRepository.findById(1).get();
	TimesheetServiceImpl.affecterMissionADepartement(1, 1);
		
	}
	@Test
	public void ajouterunTimesheet(){
		Mission	m1=missionRepository.findById(1).get();
		Employe e1=employeRepository.findById(2).get();
		Date dateDebut=new Date();
		Date dateFin=new Date();
		TimesheetServiceImpl.ajouterTimesheet(m1.getId(), e1.getId(), dateDebut, dateFin);
		
	}
	
	@Test
	public void validerTimesheet(){
		Mission	m1=missionRepository.findById(1).get();
		Employe validateurId=employeRepository.findById(2).get();
		Employe e1=employeRepository.findById(3).get();
		Date dateDebut=new Date();
		Date dateFin=new Date();
		TimesheetServiceImpl.validerTimesheet(m1.getId(), e1.getId(), dateDebut, dateFin, validateurId.getId());
		
	}
}
