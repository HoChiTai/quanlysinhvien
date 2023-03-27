package sgu.mhpl.school.coursemanager.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyDatabaseManager {
	private Connection c;
	private Statement s;
	private PreparedStatement p;
	private String host, port, dbName, dbUser, dbPassword;
	
	protected MyDatabaseManager() {
		host = "localhost";
		port = "3306";
		dbUser = "root";
		dbName = "school";
		dbPassword = "";
	}
	
	public void connectDB() {
		String dbPath = "jdbc:mysql://" + host + ":" + port + "/"
				+ dbName + "?useUnicode=yes&characterEncoding=UTF-8";
		try {
			c = (Connection) DriverManager.getConnection(dbPath, dbUser, dbPassword);
			s =  (Statement) c.createStatement();
			System.out.print("Connected");

		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
		
	}
	
	public ResultSet doReadQuery(String sql) {
		ResultSet rs = null;
		try {
			rs = ((java.sql.Statement) s).executeQuery(sql);
		} catch (SQLException ex) {
			Logger.getLogger(MyDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		return rs;
	}

	public Connection getConnection() {
		return this.c;
	}
	
	
}
