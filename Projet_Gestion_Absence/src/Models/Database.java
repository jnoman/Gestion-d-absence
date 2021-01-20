package Models;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
	
	private String url;
	private String user;
	private String pwd;
	Connection con;
	public Database() {
		super();
		this.url = "jdbc:mysql://localhost:3306/gestion_absence";
		this.user = "root";
		this.pwd = "";
	}
	public void Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection(url,user,pwd);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
	

	
	
	
	

}
