package Models;

import java.sql.Connection;

public class Database {
	private String url;
	private String user;
	private String pwd;
	Connection con;
	public Database(){
		super();
		this.url = "jdbc:mysql://localhost:3306/gestion_absence";
		this.user = "root";
		this.pwd = "";
	}
}
