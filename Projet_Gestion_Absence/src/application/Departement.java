package application;

public class Departement {
   private int id;
   private int nomDepartement;
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNomDepartement() {
		return nomDepartement;
	}
	public void setNomDepartement(int nomDepartement) {
		this.nomDepartement = nomDepartement;
	}
	
	public Departement(int id, int nomDepartement) {
		this.id = id;
		this.nomDepartement = nomDepartement;
	}
   
	
}