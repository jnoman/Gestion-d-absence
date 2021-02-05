package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Administrateur;
import Models.Apprenant;
import Models.Departement;
import Models.Formateur;
import Models.Presence;
import Models.Promo;
import Models.Secretaire;
import Models.User;
import application.Main;

import java.sql.Statement;

public class DatabaseConnection implements InterfaceDb {

	@Override
	public User authentification(String email, String password) {
		User user = null;
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "select * from user where Email='" + email + "' and password='" + password + "'";
			Statement statement = cnx.createStatement();
			ResultSet res = statement.executeQuery(sql);
			if (res.next()) {
				if (res.getString("role").equals("apprenant")) {
					user = new Apprenant(res.getInt(1), res.getString(2), res.getString(3), res.getInt(6));
				} else if (res.getString("role").equals("formateur")) {
					user = new Formateur(res.getInt(1), res.getString(2), res.getString(3));
				} else if (res.getString("role").equals("secretaire")) {
					user = new Secretaire(res.getInt(7), res.getInt(1), res.getString(2), res.getString(3));
				} else if (res.getString("role").equals("admin")) {
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
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "select * from departement";
			Statement statement = cnx.createStatement();
			ResultSet res = statement.executeQuery(sql);
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
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "SELECT * FROM `promo` WHERE id_Dep=" + idDepartement;
			Statement statement = cnx.createStatement();
			ResultSet res = statement.executeQuery(sql);
			while (res.next()) {
				promos.add(new Promo(res.getInt(1), idDepartement, res.getString(2), res.getInt(6), res.getDate(3),
						res.getDate(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return promos;
	}

	@Override
	public Promo getPromotionsByForrmateur(int idFormateur) {
		Promo promo = null;
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "SELECT * FROM `promo` WHERE `Date_debut_scolaire`<CURRENT_DATE and `Date_Fin_scolaire`>CURRENT_DATE and id_user_formateur=" + idFormateur;
			Statement statement = cnx.createStatement();
			ResultSet res = statement.executeQuery(sql);
			while (res.next()) {
				promo = new Promo(res.getInt(1), res.getInt(5), res.getString(2), res.getInt(6), res.getDate(3),res.getDate(4));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return promo;
	}

	@Override
	public int inscription(User user) {
		int ret = 0;
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "INSERT INTO `user`(`nom_complet`, `Email`, `Role`, `password`, `id_promo`, `id_Dep`) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getNomComplet());
			ps.setString(2, user.getEmail());
			ps.setString(4, user.getPassword());
			if (user.getClass() == Apprenant.class) {
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
				Secretaire secretaire = (Secretaire) user;
				ps.setNull(5, java.sql.Types.INTEGER);
				ps.setInt(6, secretaire.getIdDepartement());
			}
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("La cr�ation de l'utilisateur a �chou�, aucune ligne n'est affect�e.");
			}
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					ret = (int) generatedKeys.getLong(1);
				} else {
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
		int ret = 0;
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "INSERT INTO `promo`(`nom_promo`, `Date_debut_scolaire`, `Date_Fin_scolaire`, `id_Dep`, `id_user_formateur`) VALUES (?,?,?,?,?)";
			PreparedStatement ps = cnx.prepareStatement(sql);
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
	public ArrayList<Apprenant> getApprenant(int idPromo) {
		ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			System.out.println(idPromo);
			String sql = "SELECT * FROM user WHERE Role='apprenant' and id_promo= " + idPromo + " and id not in (SELECT id_apprenant from presence where Date_absence= CURRENT_DATE)";
			Statement statement = cnx.createStatement();
			ResultSet res = statement.executeQuery(sql);
			while (res.next()) {
				apprenants.add(new Apprenant(res.getInt(1), res.getString(2), res.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apprenants;
	}
	
	
	
	
	public ArrayList<Apprenant> getApprenant_Secretaire(int idPromo) {
		ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			System.out.println(idPromo);
			String sql = "SELECT * FROM user WHERE Role='apprenant' and id_promo= " + idPromo;
			Statement statement = cnx.createStatement();
			ResultSet res = statement.executeQuery(sql);
			while (res.next()) {
				apprenants.add(new Apprenant(res.getInt(1), res.getString(2), res.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apprenants;
	}

	@Override
	public int addAbsence(Presence presence) {
		// TODO Auto-generated method stub
		int affectedRows = 0;
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "INSERT INTO `presence`(`id_apprenant`,`id_Formateur`,`absence`,`Date_absence`,`Duree`) VALUES (?,?,?,?,?)";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, presence.getIdApprenat());
			ps.setInt(2, presence.getIdFormateur());
			ps.setBoolean(3, presence.getAbsence());
			ps.setDate(4, presence.getDateAbsence());
			ps.setFloat(5, presence.getDureAbsence());
			affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("La cr�ation de la presence a echouer");
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	@Override
	public ArrayList<Presence> getListAbsence(int idApprenant) {
		ArrayList<Presence> presences = new ArrayList<Presence>();
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "select * from presence WHERE justifier is NULL and absence=1 and id_apprenant=" + idApprenant;
			Statement statement = cnx.createStatement();
			ResultSet res = statement.executeQuery(sql);
			while (res.next()) {
				presences.add(new Presence(res.getInt(1), idApprenant, res.getInt(3), res.getBoolean(4), res.getDate(5), res.getFloat(6), res.getBoolean(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return presences;
	}

	@Override
	public int justifierAbsence(int idPresence, boolean justifier) {
		int affectedRows = 0;
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "UPDATE `presence` SET `justifier`=? WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setBoolean(1, justifier);
			ps.setInt(2, idPresence);
			affectedRows = ps.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	@Override
	public ArrayList<Presence> getFichePresence(int idApprenant, int month) {
		ArrayList<Presence> presences = new ArrayList<Presence>();
		try {
			Database con = new Database();
			Connection cnx = con.getConnection();
			String sql = "SELECT * FROM presence WHERE Month(Date_absence) ="+month+"  and id_apprenant=" + idApprenant;
			Statement statement = cnx.createStatement();
			ResultSet res = statement.executeQuery(sql);
			while (res.next()) {
				presences.add(new Presence(res.getInt(1), idApprenant, res.getInt(3), res.getBoolean(4), res.getDate(5), res.getFloat(6), res.getBoolean(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return presences;
	}

}