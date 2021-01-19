package application;

public class Promo {
	private int id;
	private String idDepartement;
	private int nomPromotion;
	private int nomFormateur;
   
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
	public int getNomFormateur() {
		return nomFormateur;
	}
	public void setNomFormateur(int nomFormateur) {
		this.nomFormateur = nomFormateur;
	}
	
	public Promo(int id, String idDepartement, int nomPromotion, int nomFormateur) {
		this.id = id;
		this.idDepartement = idDepartement;
		this.nomPromotion = nomPromotion;
		this.nomFormateur = nomFormateur;
	}
   
}