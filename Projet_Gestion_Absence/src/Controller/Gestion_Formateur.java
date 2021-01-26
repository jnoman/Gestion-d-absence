package Controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


import Models.Apprenant;
import Models.Departement;
import Models.Formateur;
import Dao.DatabaseConnection;
import Models.Presence;
import Models.Promo;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Gestion_Formateur implements Initializable {
	
	@FXML	Label nom_user;
	@FXML   ComboBox<Apprenant> comboBox;
    @FXML   javafx.scene.control.TextField txt_Field; 
    @FXML RadioButton rdb, rdb1, rdb2, rdb3;
    @FXML Label label_retard;
    float min;
	DatabaseConnection db;
	Promo promo;
	Formateur formateur;
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		nom_user.setText("bonjour " + Main.logged.getNomComplet());
		
	    db = new DatabaseConnection();
		formateur = (Formateur)Main.logged;
		promo = db.getPromotionsByForrmateur(formateur.getId());
		txt_Field.setVisible(false);
		label_retard.setVisible(false);
	    ObservableList<Apprenant> list = FXCollections.observableArrayList();
		list.addAll(db.getApprenant(promo.getId()));
		comboBox.setItems(list);
		comboBox.setConverter(new StringConverter<Apprenant>() {

			@Override
			public String toString(Apprenant apprenant) {
				if (apprenant != null) {
					return apprenant.getNomComplet();
				}
				return "";
			}

			@Override
			public Apprenant fromString(String string) {
				return null;
			}
		});
	}
	
	public void isSelected() {
		if(rdb2.isSelected()){
			txt_Field.setVisible(true);
			label_retard.setVisible(true);
		}
		else {
			txt_Field.setVisible(false);
			label_retard.setVisible(false);
		}
		
	}
	
	public void Traitement() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
 	    Date date = new Date();
 	    int id_test = 0;
 	    try {
	    if(!comboBox.getSelectionModel().getSelectedItem().toString().equals("") ){
	    	if(rdb.isSelected() ){
				Presence Pre = new Presence(comboBox.getSelectionModel().getSelectedItem().getId(),formateur.getId(),false,formatter.format(date).toString(),0);
				id_test = db.addAbsence(Pre);
			} else if(rdb1.isSelected() ){
				Presence Pre = new Presence(comboBox.getSelectionModel().getSelectedItem().getId(),formateur.getId(),true,formatter.format(date).toString(),420);
				id_test = db.addAbsence(Pre);
			}else if(rdb2.isSelected()) {
			    	  if(!txt_Field.getText().toString().equals("")){
			    	min	 = 	 Float.parseFloat(txt_Field.getText().toString());
			     
					Presence Pre = new Presence(comboBox.getSelectionModel().getSelectedItem().getId(),formateur.getId(),true,formatter.format(date).toString(),min);	
					id_test = db.addAbsence(Pre);
			    		  
			    	  }
			} else if(rdb3.isSelected()) {
			     
					Presence Pre = new Presence(comboBox.getSelectionModel().getSelectedItem().getId(),formateur.getId(),true,formatter.format(date).toString(),180);	
					id_test = db.addAbsence(Pre);
			}
			if(id_test==1) {

			    Main.getAlert("Ajoute bien effectuer", "Success");
			}
	    }	
 	    }catch(Exception e) {
 	    	
 	    	 Main.getAlert("champs vide", "Erreur");
 	    	
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
   
}