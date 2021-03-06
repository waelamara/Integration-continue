package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		l.info("In ajouterEntreprise");
		entrepriseRepoistory.save(entreprise);
		l.info("Out ajouterEntreprise");
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		l.info("In ajouterDepartement");
		deptRepoistory.save(dep);
		l.info("Out ajouterDepartement");
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				l.info("In affecterDepartementAEntreprise");
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
				l.info("Out affecterDepartementAEntreprise");
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			l.info("DepartementsNames++++:" +dep.getName());
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		l.info("In deleteEntrepriseById");
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	
		l.info("Out deleteEntrepriseById");
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		l.info("In deleteDepartementById");
		deptRepoistory.delete(deptRepoistory.findById(depId).get());
		l.info("Out deleteDepartementById");
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		l.info("In getEntrepriseById where id= "+entrepriseId);
		//return entrepriseRepoistory.findById(entrepriseId).get();
		Entreprise e = entrepriseRepoistory.findById(entrepriseId).get();
		l.info("Name_enterprise returned :"+e.getName());
		return e;
		
	}

}
