package Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import Dao.DatabaseConnection;
import Models.Apprenant;
import Models.Departement;
import Models.Formateur;
import Models.Promo;
import Models.Secretaire;
import Models.User;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PageAdminController implements Initializable {
	@FXML
	private Label nom_user, label_promo, label_date_debut, label_date_fin;
	@FXML
	private Button btnAjouter, btndeconnexion;
	@FXML
	private ComboBox<String> combo_role;
	@FXML
	private TextField txt_nomComplet, txt_email, txt_promo;
	@FXML
	private ComboBox<Departement> combo_departement;
	@FXML
	private ComboBox<Promo> combo_promo;
	@FXML
	private DatePicker picker_date_debut, picker_date_fin;

	DatabaseConnection db_con = new DatabaseConnection();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		nom_user.setText("bonjour " + Main.logged.getNomComplet());
		combo_role.getItems().addAll("apprenant", "formateur", "secrétaire");

		ObservableList<Departement> list = FXCollections.observableArrayList();
		list.addAll(db_con.getDepartement());
		combo_departement.setItems(list);
		combo_departement.setConverter(new StringConverter<Departement>() {

			@Override
			public String toString(Departement departement) {
				if (departement != null) {
					return departement.getNomDepartement();
				}
				return "";
			}

			@Override
			public Departement fromString(String string) {
				return null;
			}
		});
	}

	private String generatePassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&?";
		return RandomStringUtils.random(12, characters);
	}

	private static void envoyerEmail(String addresseMail, String nomComplet, String password) {
		try {
			HtmlEmail email = new HtmlEmail();

			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("gestion.absence.youcode@gmail.com", "M4$H7gB%p8QZ"));
			email.setSSLOnConnect(true);

			email.setFrom("gestion.absence.youcode@gmail.com");

			email.setSubject("votre account d'absence");

			email.setHtmlMsg("<html><h2>bonjour : " + nomComplet + "</h2><br/><p>username : " + addresseMail
					+ "</p><br/> <p>mot de passe : " + password + "</p> </html>");

			email.addTo(addresseMail);
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean validate_email(String email) {
		Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = regex.matcher(email);
		return matcher.find();
	}

	private static boolean netIsAvailable() {
		try {
			final URL url = new URL("http://www.google.com");
			final URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			return true;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			return false;
		}
	}

	@FXML
	private void ajouter(ActionEvent event) {
		if (!txt_nomComplet.getText().equals("") && !txt_email.getText().equals("")
				&& !combo_departement.getSelectionModel().isEmpty()
				&& ((combo_role.getSelectionModel().getSelectedItem() == "apprenant"
						&& !combo_promo.getSelectionModel().isEmpty())
						|| (combo_role.getSelectionModel().getSelectedItem() == "formateur"
								&& !txt_promo.getText().equals(""))
						|| (combo_role.getSelectionModel().getSelectedItem() == "secrétaire"))) {
			if (validate_email(txt_email.getText())) {
				if (netIsAvailable()) {
					User user;
					String password = generatePassword();
					if (combo_role.getSelectionModel().getSelectedItem() == "apprenant") {
						user = new Apprenant(txt_nomComplet.getText(), txt_email.getText(), password,
								combo_promo.getSelectionModel().getSelectedItem().getId());
					} else if (combo_role.getSelectionModel().getSelectedItem() == "formateur") {
						user = new Formateur(txt_nomComplet.getText(), txt_email.getText(), password);
					} else {
						user = new Secretaire(combo_departement.getSelectionModel().getSelectedItem().getId(),
								txt_nomComplet.getText(), txt_email.getText(), password);
					}
					int iduser = db_con.inscription(user);
					if (combo_role.getSelectionModel().getSelectedItem() == "formateur") {
						db_con.cratePromo(new Promo(combo_departement.getSelectionModel().getSelectedItem().getId(),
								txt_promo.getText(), iduser, java.sql.Date.valueOf(picker_date_debut.getValue()),
								java.sql.Date.valueOf(picker_date_fin.getValue())));
					}
					Main.getAlert("La création d'utilisateur est terminée avec succès", "utilisateur");
					envoyerEmail(txt_email.getText(), txt_nomComplet.getText(), password);
					
					txt_email.setText(null);
					txt_nomComplet.setText(null);
					txt_promo.setText(null);
					combo_role.getSelectionModel().select("secrétaire");
					combo_role.getSelectionModel().select(null);
					combo_departement.getSelectionModel().select(null);
					combo_promo.getSelectionModel().select(null);
				} else {
					Main.getAlert("Merci de vérifier votre connexion internet", "erreur");
				}
			} else {
				Main.getAlert("adresse e-mail est invalide", "erreur");
			}
		} else {
			Main.getAlert("Merci de remplire tous les champs", "erreur");
		}
	}

	@FXML
	private void deconnexion(ActionEvent event) throws IOException {
		Parent PageApprenant = FXMLLoader.load(getClass().getResource("../View/pageLogin.fxml"));
		Scene s = new Scene(PageApprenant);
		Stage page = (Stage) ((Node) event.getSource()).getScene().getWindow();
		page.setScene(s);
		page.show();
	}

	@FXML
	private void listPromo(ActionEvent event) {
		if (!combo_departement.getSelectionModel().isEmpty()) {
			if (combo_role.getSelectionModel().getSelectedItem() == "apprenant") {
				label_promo.setVisible(true);
				combo_promo.setVisible(true);
				txt_promo.setVisible(false);
				label_date_debut.setVisible(false);
				label_date_fin.setVisible(false);
				picker_date_debut.setVisible(false);
				picker_date_fin.setVisible(false);

				ObservableList<Promo> list = FXCollections.observableArrayList();
				list.addAll(db_con
						.getPromotionsByDepartement(combo_departement.getSelectionModel().getSelectedItem().getId()));
				combo_promo.setItems(list);
				combo_promo.setConverter(new StringConverter<Promo>() {

					@Override
					public String toString(Promo promo) {
						if (promo != null) {
							return promo.getNomPromotion();
						}
						return "";
					}

					@Override
					public Promo fromString(String string) {
						return null;
					}
				});
			} else if (combo_role.getSelectionModel().getSelectedItem() == "formateur") {
				label_promo.setVisible(true);
				combo_promo.setVisible(false);
				txt_promo.setVisible(true);
				label_date_debut.setVisible(true);
				label_date_fin.setVisible(true);
				picker_date_debut.setVisible(true);
				picker_date_fin.setVisible(true);
			} else if (combo_role.getSelectionModel().getSelectedItem() == "secrétaire") {
				label_promo.setVisible(false);
				combo_promo.setVisible(false);
				txt_promo.setVisible(false);
				label_date_debut.setVisible(false);
				label_date_fin.setVisible(false);
				picker_date_debut.setVisible(false);
				picker_date_fin.setVisible(false);
			}
		} else {
			combo_promo.getItems().removeAll(combo_promo.getItems());
		}
	}
}
