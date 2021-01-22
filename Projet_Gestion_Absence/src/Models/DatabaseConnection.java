package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Main;

import java.sql.Statement;

public class DatabaseConnection implements InterfaceDb {

	@Override
	public User authentification(String email, String password) {
		User user = null;
		try {
			Database con=new Database();
			Connection cnx=con.getConnection();
			String  sql="select * from user where Email='"+email+"' and password='"+password+"'";
	        Statement statement;
			statement = cnx.createStatement();
	        ResultSet res= statement.executeQuery(sql);
			if(res.next()) {
				if(res.getString("role").equals("apprenant")) {
					user = new Apprenant(res.getInt(1), res.getString(2), res.getString(3), res.getInt(6));
				} else if(res.getString("role").equals("formateur")) {
					user = new Formateur(res.getInt(1), res.getString(2), res.getString(3));
				} else if(res.getString("role").equals("secretere")) {
					user =new Secretaire(res.getInt(7), res.getInt(1), res.getString(2), res.getString(3));
				} else if(res.getString("role").equals("admin")) {
					user = new Administrateur(res.getInt(1), res.getString(2), res.getString(3));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Main.getAlert("échec de la connection a base de donnée", "erreur");
		}
		return user;
	}

	@Override
	public ArrayList<Departement> getDepartement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Promo> getPromotion(int idDepartement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int inscription(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cratePromo(Promo promo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Apprenant> getApprenant(int idPromo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addAbsence(Presence presence) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Presence> getListAbsence(int idApprenant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int justifierAbsence(int idPresence) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Presence> getFichePresence(int idApprenant) {
		// TODO Auto-generated method stub
		return null;
	}

}