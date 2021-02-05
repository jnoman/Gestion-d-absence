/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.awt.ScrollPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Dao.DatabaseConnection;
import Models.Apprenant;
import Models.Presence;
import Models.Promo;
import Models.Secretaire;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PageSecretereController implements Initializable{
	
	@FXML Label nom_user;
	@FXML ComboBox<Promo> combo_promo;
	@FXML ComboBox<Apprenant> combo_apprenant;
	@FXML Pane pane_absence;
	Secretaire secretaire;
	DatabaseConnection db;
	ArrayList<Presence> listAbsence;
	int x,y;
	ActionEvent ev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        secretaire =(Secretaire) Main.logged;
        db = new DatabaseConnection();
        
        nom_user.setText("bonjour " + Main.logged.getNomComplet());
        
        ObservableList<Promo> list = FXCollections.observableArrayList();
		list.addAll(db.getPromotionsByDepartement(secretaire.getIdDepartement()));
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
    }    
    
    @FXML
	private void selectPromo(ActionEvent event) {
    	pane_absence.getChildren().clear();
    	ObservableList<Apprenant> list = FXCollections.observableArrayList();
		list.addAll(db.getApprenant_Secretaire(combo_promo.getSelectionModel().getSelectedItem().getId()));
		combo_apprenant.setItems(list);
		combo_apprenant.setConverter(new StringConverter<Apprenant>() {

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
    
    @FXML
    private void combo_appenant(ActionEvent event) {
    	pane_absence.getChildren().clear();
    	ev = event;
    	listAbsence = new ArrayList<Presence>();
    	try {
    		listAbsence.addAll(db.getListAbsence(combo_apprenant.getSelectionModel().getSelectedItem().getId()));
    	}catch(Exception e) {
    		//null
    	}
    	
    	y=10;
    	for (Presence presence : listAbsence) {
    		x=10;
			Label date_absence = new Label(presence.getDateAbsence().toString());
			date_absence.relocate(x, y);
			pane_absence.getChildren().add(date_absence);
			x+=110;
			Label duree = new Label(presence.getDateAbsence().toString());
			if (presence.getDureAbsence() == 420) {
				duree.setText("absence journee");
			} else if (presence.getDureAbsence() == 180){
				duree.setText("absence demi journee");
			} else {
				duree.setText("retard "+presence.getDureAbsence()+" minute");
			}
			duree.relocate(x, y);
			pane_absence.getChildren().add(duree);
			
			x+=150;
			
			Button justifier = new Button("justifié");
			justifier.relocate(x, y);
			pane_absence.getChildren().add(justifier);
			justifier.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					int a = presence.getId();
					justifier(a, true);
				}
			});
			
			x+=70;
			
			Button nonJustifier = new Button("non justifié");
			nonJustifier.relocate(x, y);
			pane_absence.getChildren().add(nonJustifier);
			nonJustifier.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					int a = presence.getId();
					justifier(a, false);
				}
			});
			y=y+40;
		}
    }
    
    private void justifier(int idPresence, boolean justifier) {
    	db.justifierAbsence(idPresence, justifier);
    	pane_absence.getChildren().clear();
    	combo_appenant(ev);
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
