/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class FXMLDocumentController implements Initializable {
    Connection connection; 
     Statement statement;
    private Label label;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button btnConnexion;
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connexion(ActionEvent event) throws IOException {
         try
    { 
        connexion con=new connexion();
      Connection cnx=con.getConnection();
    String  sql="select * from user where Email='"+email.getText()+"' and password='"+password.getText()+"'";
        statement=cnx.createStatement();
        ResultSet res= statement.executeQuery(sql);
        if(res.next()){
         if(res.getString("role").equals("apprenant")){
              Parent PageApprenant=FXMLLoader.load(getClass().getResource("pageApprenant.fxml"));
              Scene s=new Scene(PageApprenant);
              Stage page=(Stage)((Node)event.getSource()).getScene().getWindow();
              page.setScene(s);
              page.show();
        }  
        if(res.getString("role").equals("formateur")){
               Parent PageFormateur=FXMLLoader.load(getClass().getResource("pageFormateur.fxml"));
              Scene s=new Scene(PageFormateur);
              Stage page=(Stage)((Node)event.getSource()).getScene().getWindow();
              page.setScene(s);
              page.show();
        }
         if(res.getString("role").equals("admin")){
              Parent PageAdmin=FXMLLoader.load(getClass().getResource("pageAdmin.fxml"));
              Scene s=new Scene(PageAdmin);
              Stage page=(Stage)((Node)event.getSource()).getScene().getWindow();
              page.setScene(s);
              page.show();
        }
          if(res.getString("role").equals("secretere")){
             Parent PageSecretere=FXMLLoader.load(getClass().getResource("pageSecretere.fxml"));
              Scene s=new Scene(PageSecretere);
              Stage page=(Stage)((Node)event.getSource()).getScene().getWindow();
              page.setScene(s);
              page.show();
        }
          }
        else{
             JOptionPane.showMessageDialog(null, "invalide!!!!!!", "error: ",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    catch (SQLException e)
    {
        e.printStackTrace();
    }   
    }
    
}
