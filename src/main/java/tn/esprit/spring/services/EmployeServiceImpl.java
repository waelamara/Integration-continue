package tn.esprit.spring.services;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class EmployeServiceImpl implements IEmployeService {
	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	@Override
	public Employe authenticate(String login, String password) {
		l.info("In authenticate");
		Employe emp =employeRepository.getEmployeByEmailAndPassword(login, password);
		l.info("Out authenticate");
		return emp ;
	}

	@Override
	public Employe addOrUpdateEmploye(Employe employe) {
		l.info("In addOrUpdateEmploye");
		employeRepository.save(employe);
		l.info("Out addOrUpdateEmploye");
		return employe;
	}

	@Override
		public Employe mettreAjourEmailByEmployeId(String email, int employeId) {
		l.info("In mettreAjourEmailByEmployeId");
		Employe employe = employeRepository.findById(employeId).get();
		employe.setEmail(email);
		employeRepository.save(employe);
		l.info("Out mettreAjourEmailByEmployeId");
		return employe;
	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		l.info("In affecterEmployeADepartement");
		Departement depManagedEntity = deptRepoistory.findById(depId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);
		}

		// à ajouter? 
		deptRepoistory.save(depManagedEntity);
		l.info("Out affecterEmployeADepartement");
	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		l.info("In desaffecterEmployeDuDepartement");
		Departement dep = deptRepoistory.findById(depId).get();

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
		l.info("Out desaffecterEmployeDuDepartement");
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		l.info("In ajouterContrat");
		contratRepoistory.save(contrat);
		l.info("Out ajouterContrat");
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		l.info("In affecterContratAEmploye");
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		l.info("EmployeId :"+employeId +" ContratId : "+contratId);
		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		l.info("Out affecterContratAEmploye");
	}

	public String getEmployePrenomById(int employeId) {
		l.info("In getEmployePrenomById where id= "+employeId);
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		l.info("Name_user returned :"+employeManagedEntity.getPrenom());
		return employeManagedEntity.getPrenom();
	}
	 
	public void deleteEmployeById(int employeId)
	{
		l.info("In deleteEmployeById");
		Employe employe = employeRepository.findById(employeId).get();

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
		l.info("Out deleteEmployeById with EmployeeId Deleted = "+employeId);
	}

	public void deleteContratById(int contratId) {
		l.info("In deleteContratById");
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		contratRepoistory.delete(contratManagedEntity);
		l.info("Out deleteContratById with contratId Deleted = "+contratId);
	}

	public int getNombreEmployeJPQL() {
		l.info(" In getNombreEmployeJPQL");
		int nbr =employeRepository.countemp();
		l.info(" Out getNombreEmployeJPQL");
		return nbr;
	}

	public List<String> getAllEmployeNamesJPQL() {
		l.info(" In getAllEmployeNamesJPQL");
		List<String> listName=employeRepository.employeNames();
		l.info(" Out getAllEmployeNamesJPQL");
		return listName;
	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		l.info(" In getAllEmployeByEntreprise");
		List<Employe> listemp=employeRepository.getAllEmployeByEntreprisec(entreprise);
		l.info(" Out getAllEmployeByEntreprise");
		return listemp;
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		l.info(" In mettreAjourEmailByEmployeIdJPQL");
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
		l.info(" Out mettreAjourEmailByEmployeIdJPQL");
	}
	public void deleteAllContratJPQL() {
		l.info(" In deleteAllContratJPQL");
		employeRepository.deleteAllContratJPQL();
		l.info(" Out deleteAllContratJPQL");
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		l.info(" In getSalaireByEmployeIdJPQL");
		float salaire=employeRepository.getSalaireByEmployeIdJPQL(employeId);
		l.info(" Out getSalaireByEmployeIdJPQL");
		return salaire;
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		l.info(" In getSalaireMoyenByDepartementId");
		Double salaire=employeRepository.getSalaireMoyenByDepartementId(departementId);
		l.info(" Out getSalaireMoyenByDepartementId");
		return salaire ;
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		l.info(" In getTimesheetsByMissionAndDate");
		List<Timesheet> list=timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
		l.info(" Out getTimesheetsByMissionAndDate");
		return list;
	}

	public List<Employe> getAllEmployes() {
		l.info("In getAllEmployes");
		List<Employe> listEmp= (List<Employe>) employeRepository.findAll();
		l.info(" Out getAllEmployes");
		return listEmp;
	}

}
