package Models;

public abstract class User {
   private int id;
   private String nomComplet;
   private String email;
   private String password;
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(int id, String nomComplet, String email) {
		this.id = id;
		this.nomComplet = nomComplet;
		this.email = email;
	}
	
	public User(String nomComplet, String email, String password) {
		this.nomComplet = nomComplet;
		this.email = email;
		this.password = password;
	}
	
	

}