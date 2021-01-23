package Models;

public class Departement {
   private int id;
   private String nomDepartement;
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomDepartement() {
		return nomDepartement;
	}
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}
	
	public Departement(int id, String nomDepartement) {
		this.id = id;
		this.nomDepartement = nomDepartement;
	}
   
	
}