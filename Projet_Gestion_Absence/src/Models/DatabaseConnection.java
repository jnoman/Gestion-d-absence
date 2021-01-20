package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DatabaseConnection implements InterfaceDb {

	@Override
	public int authentification(String email, String password) {
		// TODO Auto-generated method stub
		return 0;
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