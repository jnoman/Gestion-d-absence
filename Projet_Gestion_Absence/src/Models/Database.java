package Models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public  Connection conx;
    public  Connection getConnection(){
    String dbName="gestion_absence";
    String user="root";
    String password="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             conx=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,user,password);
        } catch (Exception ex) {
           System.out.print(ex); ;
        }
        return conx;
    }
}
