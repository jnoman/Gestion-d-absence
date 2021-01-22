/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

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
        
    }    
    
    public String generatePassword() {
    	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&?";
    	return RandomStringUtils.random( 12, characters );
    }
    
    public static void envoyerEmail(String addresseMail, String nomComplet, String password) {
        try {            
        	HtmlEmail  email = new HtmlEmail();
            
            // Configuration
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("gestion.absence.youcode@gmail.com", "M4$H7gB%p8QZ"));
            email.setSSLOnConnect(true);
            
            // Sender
            email.setFrom("gestion.absence.youcode@gmail.com");
            
            // Email title
            email.setSubject("votre account d'absence");
            
            // Email message.
            email.setHtmlMsg("<html><h2>bonjour : "+nomComplet+"</h2><br/><p>username : "+addresseMail+"</p><br/> <p>mot de passe : "+password+"</p> </html>");
            // Receiver
            email.addTo(addresseMail);            
            email.send();
            System.out.println("Sent!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
