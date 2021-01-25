package Controller;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.util.StringConverter;

public class Gestion_Formateur implements Initializable {
	
 	 
	@FXML   ComboBox<Apprenant> comboBox;
    @FXML   javafx.scene.control.TextField txt_Field; 
    @FXML   DatePicker datepicker;
    @FXML RadioButton rdb1;
    float min,houre;
    @FXML RadioButton rdb2;
	DatabaseConnection db;
	Promo promo;
	Formateur formateur;
	@Override
	public void initialize(URL url,ResourceBundle rb) {
	    db = new DatabaseConnection();
		formateur = (Formateur)Main.logged;
		promo = db.getPromotionsByForrmateur(formateur.getId());
		datepicker.setVisible(true);
		txt_Field.setVisible(false);
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
		
		System.out.println("hello");
		System.out.println(rdb1.isSelected());
		if(rdb1.isSelected()) {
			datepicker.setVisible(true);
			txt_Field.setVisible(false);
		}else if(rdb2.isSelected()){
			datepicker.setVisible(false);
			txt_Field.setVisible(true);
		}
		
	}
	
	public void Traitement() {
		int id_test;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
 	    Date date = new Date();
 	    try {
	    if(!comboBox.getSelectionModel().getSelectedItem().toString().equals("") ){
			if(rdb1.isSelected() ){
				if(datepicker.getValue().toString().equals("")) {
					Main.getAlert("champs vide", "Erreur");
				}else {
				    Presence Pre = new Presence(comboBox.getSelectionModel().getSelectedItem().getId(),formateur.getId(),true,datepicker.getValue().toString(),420);
				    id_test = db.addAbsence(Pre);
				    Main.getAlert("Ajoute bien effectuer", "Success");
					 
				}
			}else {
			      if(rdb2.isSelected()) {
			    if(!txt_Field.getText().toString().equals("")){
			    min	 = 	 Float.parseFloat(txt_Field.getText().toString());
			    houre = min/60;
			   
				      if(houre == 7.0 || houre == 3.0) { 
				    	 // System.out.println(formatter.format(date).toString());  
						 Presence Pre = new Presence(comboBox.getSelectionModel().getSelectedItem().getId(),formateur.getId(),true,formatter.format(date).toString(),min);	
						 id_test = db.addAbsence(Pre);
						 Main.getAlert("Ajoute bien effectuer", "Success");
				    	}else {
						    Presence Pre = new Presence(comboBox.getSelectionModel().getSelectedItem().getId(),formateur.getId(),false,formatter.format(date).toString(),min);	
						    id_test = db.addAbsence(Pre);
						    Main.getAlert("Ajoute bien effectuer", "Success");
				    		
				    	}
			    		  
			    	  }
			    }
			}

	    }	
 	    }catch(Exception e) {
 	    	
 	    	 Main.getAlert("champs vide", "Erreur");
 	    	
 	    }
	    	
	   

		
	}
	
	
   
}