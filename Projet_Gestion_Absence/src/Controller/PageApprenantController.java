/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Apprenant;
import Models.Month;
import Dao.DatabaseConnection;
import Models.Presence;
import application.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PageApprenantController implements Initializable {
private Apprenant apprenant;
    /**
     * Initializes the controller class.
     */
    int id=0, x, y;
    @FXML
    private ComboBox<Month> mois;
    @FXML Label nom_user;
    @FXML Pane pane_fiche;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	nom_user.setText("bonjour " + Main.logged.getNomComplet());
        ArrayList<Month> months =new ArrayList<Month>();
        months.add(new Month("octobre",10));
        months.add(new Month("novembre",11));
        months.add(new Month("decembre",12));
        months.add(new Month("janvier",1));
        months.add(new Month("fevrier",2));
        months.add(new Month("mars",3));
        months.add(new Month("avril",4));
        months.add(new Month("mai",5));
        months.add(new Month("juin",6));
        months.add(new Month("juillet",7));
        months.add(new Month("aout",8));
        months.add(new Month("septembre",9));
        
        ObservableList<Month> list = FXCollections.observableArrayList();
		list.addAll(months);
		mois.setItems(list);
		mois.setConverter(new StringConverter<Month>() {

			@Override
			public String toString(Month month1) {
				if (month1 != null) {
					return month1.getNom();
				}
				return "";
			}

			@Override
			public Month fromString(String string) {
				return null;
			}
		});
        
    }    

    @FXML
    private void selectMois(ActionEvent event) {
    	pane_fiche.getChildren().clear();
    	System.out.println(mois.getSelectionModel().getSelectedItem().getId());
    	ArrayList<Presence> presences =new ArrayList<Presence>();
    	DatabaseConnection db =new DatabaseConnection();
    	presences.addAll(db.getFichePresence(Main.logged.getId(), mois.getSelectionModel().getSelectedItem().getId()));
    	
    	
    	y=10;
    	for (Presence presence : presences) {
    		x=10;
			Label date_presence = new Label(presence.getDateAbsence().toString());
			date_presence.relocate(x, y);
			pane_fiche.getChildren().add(date_presence);
			x+=110;
			Label duree = new Label(presence.getDateAbsence().toString());
			if (presence.getAbsence() == false) {
				duree.setText("Present");
			} else if (presence.getDureAbsence() == 420) {
				duree.setText("absence journee");
			} else if (presence.getDureAbsence() == 180){
				duree.setText("absence demi journee");
			} else {
				duree.setText("retard "+presence.getDureAbsence()+" minute");
			}
			duree.relocate(x, y);
			pane_fiche.getChildren().add(duree);
			
			
			y=y+40;
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
