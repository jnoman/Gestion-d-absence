package Models;

import java.sql.Date;

public class Promo {
	private int id;
	private String idDepartement;
	private int nomPromotion;
	private int idFormateur;
	private Date dateDebutAnneeScolaire;
	private Date dateFinAnneeScolaire;
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdDepartement() {
		return idDepartement;
	}
	public void setIdDepartement(String idDepartement) {
		this.idDepartement = idDepartement;
	}
	public int getNomPromotion() {
		return nomPromotion;
	}
	public void setNomPromotion(int nomPromotion) {
		this.nomPromotion = nomPromotion;
	}
	public int getIdFormateur() {
		return idFormateur;
	}
	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}
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
	
	
	public Promo(int id, String idDepartement, int nomPromotion, int idFormateur, Date dateDebutAnneeScolaire, Date dateFinAnneeScolaire) {
		this.id = id;
		this.idDepartement = idDepartement;
		this.nomPromotion = nomPromotion;
		this.idFormateur = idFormateur;
		this.dateDebutAnneeScolaire = dateDebutAnneeScolaire;
		this.dateFinAnneeScolaire = dateFinAnneeScolaire;
	}
	public Promo(String idDepartement, int nomPromotion, int idFormateur, Date dateDebutAnneeScolaire, Date dateFinAnneeScolaire) {
		this.idDepartement = idDepartement;
		this.nomPromotion = nomPromotion;
		this.idFormateur = idFormateur;
		this.dateDebutAnneeScolaire = dateDebutAnneeScolaire;
		this.dateFinAnneeScolaire = dateFinAnneeScolaire;
	}
   
}