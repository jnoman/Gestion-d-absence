package Models;

public class Secretaire extends User {
   	private int idDepartement;

	public int getIdDepartement() {
		return idDepartement;
	}
	
	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}
   
	public Secretaire(int idDepartement, int id, String nomComplet, String email) {
		super(id, nomComplet, email);
		this.idDepartement = idDepartement;
	}
	
	public Secretaire(int idDepartement, String nomComplet, String email ,String password) {
		super(nomComplet, email, password);
		this.idDepartement = idDepartement;
	}

}