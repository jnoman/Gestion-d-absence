package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements InterfaceDb {

	@Override
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/skills", "root","");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.toString());;
		}
		return con;
	}

	@Override
	public int authentification() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getDepartement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPromotion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User inscription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getApprenant() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addAbsence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getListAbsence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int justifierAbsence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getFichePresence() {
		// TODO Auto-generated method stub
		
	}
}