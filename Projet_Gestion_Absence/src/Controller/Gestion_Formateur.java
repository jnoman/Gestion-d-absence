package Controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


import Models.Apprenant;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Gestion_Formateur implements Initializable {
	
	@FXML Label nom_user;
	@FXML ComboBox<Apprenant> comboBox;
    @FXML javafx.scene.control.TextField txt_Field; 
    @FXML RadioButton rdb, rdb1, rdb2, rdb3;
    @FXML Label label_retard;
    @FXML private TableView<Apprenant> table;
    @FXML private TableColumn<Apprenant,Integer> id;
    @FXML private TableColumn<Apprenant,String> name;
    @FXML private TableColumn<Apprenant,String> gmail;
          private List<Apprenant>  list;
          private List<Integer>  list_selected;
          private ObservableList<Apprenant> liste;
          int	idSelected = 0;

    float min;
	DatabaseConnection db;
	Promo promo;
	Formateur formateur;
	@Override
	public void initialize(URL url,ResourceBundle rb) {
		try {
			list = new ArrayList<Apprenant>();
			list_selected = new ArrayList<Integer>();
			list_selected.clear();
		    id.setCellValueFactory(new PropertyValueFactory<Apprenant,Integer>("id"));
		    name.setCellValueFactory(new PropertyValueFactory<Apprenant,String>("nomComplet"));
		    gmail.setCellValueFactory(new PropertyValueFactory<Apprenant,String>("Email"));
			nom_user.setText("bonjour " + Main.logged.getNomComplet());
		    db = new DatabaseConnection();
			formateur = (Formateur)Main.logged;
			promo = db.getPromotionsByForrmateur(formateur.getId());
			txt_Field.setVisible(false);
			label_retard.setVisible(false);
			RemplirTable_1();
		}catch(Exception e) {
			//null
		}
	}
	
	public void RemplirTable_2() {
		
		liste = FXCollections.observableArrayList();
		list.clear();
		list.addAll(db.getApprenant(promo.getId()));		
 
		System.out.println(list_selected.size());
		
		for(int i=0;i<list.size();i++) {
			
			for(int j=0;j<list_selected.size();j++) {
				if(list.get(i).getId() == list_selected.get(j) ) {
					System.out.println(list_selected.get(j));
					list.remove(i);
						
					}	
			}

			
		}
	    for(int i =0 ;i<list.size();i++) {
	    	liste.add(list.get(i));
	    }
	    for ( int i = 0; i<table.getItems().size(); i++) {
	        table.getItems().clear();
	    }
	    table.setItems(liste);
		
	}
public void RemplirTable_1() {
		
		liste = FXCollections.observableArrayList();
		list.clear();
		list.addAll(db.getApprenant(promo.getId()));		
	    for(int i =0 ;i<list.size();i++) {
	    	System.out.println(list.get(i));
	    	liste.add(list.get(i));
	    }
	    for ( int i = 0; i<table.getItems().size(); i++) {
	    	System.out.println("table");
	        table.getItems().clear();
	    }
	    table.setItems(liste);
		
	}
	@FXML
	public void select() {
		
		idSelected = table.getSelectionModel().getSelectedItem().getId();
		System.out.println(idSelected);
		
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
 	    java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
 	    System.out.println(date);
 	    int id_test = 0;
 	    try {
	    if(idSelected != 0){
	    	list_selected.add(idSelected);
	    	if(rdb.isSelected() ){
	    		System.out.println(date);
				Presence Pre = new Presence(idSelected,formateur.getId(),false,date,0);
				id_test = db.addAbsence(Pre);
				System.out.println(id_test);
				RemplirTable_2();
			} else if(rdb1.isSelected() ){
				Presence Pre = new Presence(idSelected,formateur.getId(),true,date,420);
				id_test = db.addAbsence(Pre);
				RemplirTable_2();
			}else if(rdb2.isSelected()) {
			    	  if(!txt_Field.getText().toString().equals("")){
			    	min	 = 	 Float.parseFloat(txt_Field.getText().toString());		     
					Presence Pre = new Presence(idSelected,formateur.getId(),true,date,min);	
					id_test = db.addAbsence(Pre);
					RemplirTable_2();
			    	  }
			} else if(rdb3.isSelected()) {
					Presence Pre = new Presence(idSelected,formateur.getId(),true,date,180);	
					id_test = db.addAbsence(Pre);
					RemplirTable_2();
			}
			if(id_test==1) {

			    Main.getAlert("Ajoute bien effectuer", "Success");
			}
	    }	
 	    }catch(Exception e) {
 	    	
 	    	 Main.getAlert("champs vide", "Erreur");
 	    	
 	    }
	    	
	   

		
	}
	
	public void test() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
 	    Date date = new Date();	
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