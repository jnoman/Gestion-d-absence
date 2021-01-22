/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.lang3.RandomStringUtils;

import application.Main;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PageAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.getAlert(generatePassword(), "password");
    }    
    
    public String generatePassword() {
    	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&?";
    	return RandomStringUtils.random( 12, characters );
    }
    
}
