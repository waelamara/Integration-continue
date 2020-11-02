package tn.esprit.spring.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Timesheet implements Serializable{

	private static final long serialVersionUID = 3876346912862238239L;

	@EmbeddedId
	private TimesheetPK timesheetPK;
	
	//idMission est a la fois primary key et foreign key
	@ManyToOne
    @JoinColumn(name = "idMission", referencedColumnName = "id", insertable=false, updatable=false)
	private Mission mission;
	
	//idEmploye est a la fois primary key et foreign key
	
	@ManyToOne
    @JoinColumn(name = "idEmploye", referencedColumnName = "id", insertable=false, updatable=false)
	private Employe employe;
	
	
	private boolean isValide;
	

	public boolean isValide() {
		return isValide;
	}

	public void setValide(boolean isValide) {
		this.isValide = isValide;
	}

	public TimesheetPK getTimesheetPK() {
		return timesheetPK;
	}

	public void setTimesheetPK(TimesheetPK timesheetPK) {
		this.timesheetPK = timesheetPK;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Timesheet(TimesheetPK timesheetPK, Mission mission, Employe employe, boolean isValide) {
		super();
		this.timesheetPK = timesheetPK;
		this.mission = mission;
		this.employe = employe;
		this.isValide = isValide;
	}

	public Timesheet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Timesheet [timesheetPK=" + timesheetPK + ", mission=" + mission + ", employe=" + employe + ", isValide="
				+ isValide + "]";
	}

	
	
}
