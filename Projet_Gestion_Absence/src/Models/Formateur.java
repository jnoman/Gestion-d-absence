package Models;

public class Formateur extends User {
	public Formateur(int id, String nomComplet, String email) {
		super(id, nomComplet, email);
	}
	
	public Formateur(String nomComplet, String email, String password) {
		super(nomComplet, email, password);
	}
}