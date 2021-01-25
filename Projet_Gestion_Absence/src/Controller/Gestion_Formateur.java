package Controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


import Models.Apprenant;
import Models.DatabaseConnection;
import Models.Presence;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

public class Gestion_Formateur implements Initializable {
	
 	 
	@FXML   ComboBox<String> comboBox;
    @FXML   javafx.scene.control.TextField txt_Field; 
    @FXML   DatePicker datepicker;
    @FXML RadioButton rdb1;
    float min,houre;
    @FXML RadioButton rdb2;
	DatabaseConnection db;
	List<Apprenant> Apprenant;
	
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		datepicker.setVisible(true);
		txt_Field.setVisible(false);
		Apprenant = new ArrayList<Apprenant>();
	    db = new DatabaseConnection();
	    Apprenant = db.getApprenant(19);
		 for(int i=0;i<Apprenant.size();i++){
			 
			 comboBox.getItems().addAll(Apprenant.get(i).getNomComplet()); 
			 
		 }
		
		
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
		int id = 0;
		int id_test;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
 	    Date date = new Date();
 	    try {
		String name = comboBox.getSelectionModel().getSelectedItem().toString();
	    if(!comboBox.getSelectionModel().getSelectedItem().toString().equals("") ){
	    	id = db.getApprenant(name);
			if(rdb1.isSelected() ){
				if(datepicker.getValue().toString().equals("")) {
					Main.getAlert("champs vide", "Erreur");
				}else {
					System.out.println(datepicker.getValue().toString());
					System.out.println(name);
					System.out.println(id); 
				    Presence Pre = new Presence(id,21,true,datepicker.getValue().toString(),420);
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
						 Presence Pre = new Presence(id,21,true,formatter.format(date).toString(),min);	
						 id_test = db.addAbsence(Pre);
						 Main.getAlert("Ajoute bien effectuer", "Success");
				    	}else {
						    Presence Pre = new Presence(id,21,false,formatter.format(date).toString(),min);	
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
