package application;

import java.sql.Date;

public class Apprenant extends User {
   private Date dateDebutAnneeScolaire;
   private Date dateFinAnneeScolaire;
   private int idPromotion;
   
	public Date getDateDebutAnneeScolaire() {
		return dateDebutAnneeScolaire;
	}
	public void setDateDebutAnneeScolaire(Date dateDebutAnneeScolaire) {
		this.dateDebutAnneeScolaire = dateDebutAnneeScolaire;
	}
	public Date getDateFinAnneeScolaire() {
		return dateFinAnneeScolaire;
	}
	public void setDateFinAnneeScolaire(Date dateFinAnneeScolaire) {
		this.dateFinAnneeScolaire = dateFinAnneeScolaire;
	}
	public int getIdPromotion() {
		return idPromotion;
	}
	public void setIdPromotion(int idPromotion) {
		this.idPromotion = idPromotion;
	}
	   
	public Apprenant(int id, String nomComplet, String email, Date dateDebutAnneeScolaire, Date dateFinAnneeScolaire, int idPromotion) {
		super(id, nomComplet, email);
		this.dateDebutAnneeScolaire = dateDebutAnneeScolaire;
		this.dateFinAnneeScolaire = dateFinAnneeScolaire;
		this.idPromotion = idPromotion;
	}
	
	public Apprenant(String nomComplet, String email, String password,Date dateDebutAnneeScolaire, Date dateFinAnneeScolaire, int idPromotion) {
		super(nomComplet, email, password);
		this.dateDebutAnneeScolaire = dateDebutAnneeScolaire;
		this.dateFinAnneeScolaire = dateFinAnneeScolaire;
		this.idPromotion = idPromotion;
	}

}