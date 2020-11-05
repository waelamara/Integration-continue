package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
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
	public void testAjouterMission() {
		Mission	mission=new Mission("nou","nou");
		int	missionadd=TimesheetServiceImpl.ajouterMission(mission);
		assertEquals(mission.getId(), missionadd);
	}
	
	@Test
	public void testAffecterMissionADepartement(){
	Mission	m1=missionRepository.findById(2).get();
	Departement d1=departementRepository.findById(1).get();
	 Mission T1 = TimesheetServiceImpl.affecterMissionADepartement(2, 1);
	assertEquals(T1.getId(), m1.getId());
	assertEquals(T1.getDepartement().getId(), d1.getId());
		
	}
	@Test
	public void testAjouterunTimesheet(){
		Mission	m1=missionRepository.findById(1).get();
		Employe e1=employeRepository.findById(3).get();
		Date dateDebut=new Date();
		Date dateFin=new Date();
	Timesheet T1=	TimesheetServiceImpl.ajouterTimesheet(m1.getId(), e1.getId(), dateDebut, dateFin);
	
	assertEquals(T1.getTimesheetPK().getIdMission(),m1.getId());
	assertEquals(T1.getTimesheetPK().getIdEmploye(),e1.getId());
	assertEquals(T1.getTimesheetPK().getDateDebut(),dateDebut);
	assertEquals(T1.getTimesheetPK().getDateFin(),dateFin);
	}
	
	@Test
	public void testValiderTimesheet(){
		Mission	m1=missionRepository.findById(1).get();
		Employe validateurId=employeRepository.findById(2).get();
		Employe e1=employeRepository.findById(3).get();
		
		Date dateDebut=new Date();
		Date dateFin=new Date();
		Timesheet T1=TimesheetServiceImpl.validerTimesheet(m1.getId(),e1.getId(), dateDebut, dateFin, validateurId.getId());
		System.out.println("T1.getTimesheetPK().getIdMission()"+T1.getTimesheetPK().getIdMission());
		System.out.println("m1.getId()"+m1.getId());
		
		
		assertEquals(T1.getTimesheetPK().getIdMission(),m1.getId());
		assertEquals(T1.getTimesheetPK().getIdEmploye(),e1.getId());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(dateFormat.format(T1.getTimesheetPK().getDateDebut()),dateFormat.format(dateDebut));
		assertEquals(dateFormat.format(T1.getTimesheetPK().getDateFin()),dateFormat.format(dateFin));

	}
}
