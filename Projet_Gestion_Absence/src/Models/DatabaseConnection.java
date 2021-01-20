package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DatabaseConnection implements InterfaceDb {

	private String url;
	private String user;
	private String pwd;
	Connection con;
	public DatabaseConnection() {
		super();
		this.url = "jdbc:mysql://localhost:3306/gestion_absence";
		this.user = "root";
		this.pwd = "";
	}
	public void Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection(url,user,pwd);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@Override
	public int authentification() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Departement> getDepartement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Promo> getPromotion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User inscription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Apprenant> getApprenant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addAbsence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Presence> getListAbsence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int justifierAbsence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Presence> getFichePresence() {
		// TODO Auto-generated method stub
		return null;
	}

	
}