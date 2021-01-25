package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Main;

import java.sql.Statement;

public class DatabaseConnection implements InterfaceDb {
	int id;
	int affectedRows;
	@Override
	public User authentification(String email, String password) {
		User user = null;
		
		try {
			Database con=new Database();
			Connection cnx=con.getConnection();
			String  sql="select * from user where Email='"+email+"' and password='"+password+"'";
	        Statement statement = cnx.createStatement();
	        ResultSet res= statement.executeQuery(sql);
			if(res.next()) {
				if(res.getString("role").equals("apprenant")) {
					user = new Apprenant(res.getInt(1), res.getString(2), res.getString(3), res.getInt(6));
				} else if(res.getString("role").equals("formateur")) {
					user = new Formateur(res.getInt(1), res.getString(2), res.getString(3));
				} else if(res.getString("role").equals("secretaire")) {
					user =new Secretaire(res.getInt(7), res.getInt(1), res.getString(2), res.getString(3));
				} else if(res.getString("role").equals("admin")) {
					user = new Administrateur(res.getInt(1), res.getString(2), res.getString(3));
				}
			}
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Main.getAlert("�chec de la connection a base de donn�e", "erreur");
		}
		return user;
	}

	@Override
	public ArrayList<Departement> getDepartement() {
		ArrayList<Departement> departements = new ArrayList<Departement>();
		try {
			Database con=new Database();
			Connection cnx=con.getConnection();
			String  sql="select * from departement";
	        Statement statement = cnx.createStatement();
	        ResultSet res= statement.executeQuery(sql);
	        while (res.next()) {
	        	departements.add(new Departement(res.getInt(1), res.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departements;
	}

	@Override
	public ArrayList<Promo> getPromotionsByDepartement(int idDepartement) {
		ArrayList<Promo> promos = new ArrayList<Promo>();
		try {
			Database con=new Database();
			Connection cnx=con.getConnection();
			String  sql="SELECT * FROM `promo` WHERE id_Dep="+idDepartement;
	        Statement statement = cnx.createStatement();
	        ResultSet res= statement.executeQuery(sql);
	        while (res.next()) {
	        	promos.add(new Promo(res.getInt(1), idDepartement, res.getString(2), res.getInt(6), res.getDate(3), res.getDate(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return promos;
	}
	
	@Override
	public ArrayList<Promo> getPromotionsByForrmateur(int idDepartement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int inscription(User user) {
		int ret =0;
		try {
			Database con=new Database();
			Connection cnx=con.getConnection();
			String sql ="INSERT INTO `user`(`nom_complet`, `Email`, `Role`, `password`, `id_promo`, `id_Dep`) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps=cnx.prepareStatement(sql);
			ps.setString(1, user.getNomComplet());
			ps.setString(2, user.getEmail());
			ps.setString(4, user.getPassword());
			if(user.getClass() == Apprenant.class) {
				Apprenant apprenat = (Apprenant) user;
				ps.setString(3, "apprenant");
				ps.setInt(5, apprenat.getIdPromotion());
				ps.setNull(6, java.sql.Types.INTEGER);
			} else if (user.getClass() == Formateur.class) {
				ps.setString(3, "formateur");
				ps.setNull(5, java.sql.Types.INTEGER);
				ps.setNull(6, java.sql.Types.INTEGER);
			} else if (user.getClass() == Secretaire.class) {
				ps.setString(3, "secretaire");
				Secretaire secretaire =(Secretaire) user;
				ps.setNull(5, java.sql.Types.INTEGER);
				ps.setInt(6, secretaire.getIdDepartement());
			}
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
	            throw new SQLException("La cr�ation de l'utilisateur a �chou�, aucune ligne n'est affect�e.");
	        }
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	ret = generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("La cr�ation de l'utilisateur a �chou�, aucun ID obtenu.");
	            }
	        }
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int cratePromo(Promo promo) {
		int ret =0;
		try {
			Database con=new Database();
			Connection cnx=con.getConnection();
			String sql ="INSERT INTO `promo`(`nom_promo`, `Date_debut_scolaire`, `Date_Fin_scolaire`, `id_Dep`, `id_user_formateur`) VALUES (?,?,?,?,?)";
			PreparedStatement ps=cnx.prepareStatement(sql);
			ps.setString(1, promo.getNomPromotion());
			ps.setDate(2, promo.getDateDebutAnneeScolaire());
			ps.setDate(3, promo.getDateFinAnneeScolaire());
			ps.setInt(4, promo.getIdDepartement());
			ps.setInt(5, promo.getIdFormateur());
			ret = ps.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Apprenant> getApprenant(int idPromo) {
		// TODO Auto-generated method stub
		List<Apprenant> user = new ArrayList<Apprenant>();
		try {
			Database con=new Database();
			Connection cnx=con.getConnection();
			String  sql="select * from user where Role='Apprenant' and id_promo='"+idPromo+"'";
	        Statement statement = cnx.createStatement();
	        ResultSet res= statement.executeQuery(sql);
			if(res.next()) {
					user.add(new Apprenant(res.getInt(1), res.getString(2), res.getString(3), res.getInt(6)));
			}
			cnx.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			Main.getAlert("�chec de la connection a base de donn�e", "erreur");
		}
		return user;
	}

	@Override
	public int addAbsence(Presence presence) {
		// TODO Auto-generated method stub
		try {
			Database con=new Database();
			Connection cnx=con.getConnection();
			String sql ="INSERT INTO `presence`(`id_apprenant`,`id_Formateur`,`absence`,`Date_absence`,`Duree`) VALUES (?,?,?,?,?)";
			PreparedStatement ps= cnx.prepareStatement(sql);
			ps.setInt(1,presence.getIdApprenat());
			ps.setInt(2,presence.getIdFormateur());
			ps.setBoolean(3,presence.getAbsence());
			ps.setString(4,presence.getDateAbsence());
			ps.setFloat(5,presence.getDureAbsence());
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0){
	            throw new SQLException("La cr�ation de la presence a echouer");
	        }
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}
	
	public int getApprenant(String name) {
		
		try {
			Database con=new Database();
			Connection cnx=con.getConnection();
			String  sql = "select id from user where nom_complet = '" + name +"'";
	        Statement statement = cnx.createStatement();
	        ResultSet res= statement.executeQuery(sql);
			if(res.next()) {
					id = res.getInt(1);
			}
			cnx.close();
		}catch(SQLException e){
			// TODO Auto-generated catch block.
			Main.getAlert("�chec de la connection a base de donn�e","erreur");
		}
		return id;
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