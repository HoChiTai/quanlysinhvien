package sgu.mhpl.school.coursemanager.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {
	private static String host = "127.0.0.1";
	private static String port = "3306";
	private static String database = "school";
	private static String username = "root";
	private static String password = "";
	private static Connection connection;
	
	public static Connection connect() {
		if(connection != null) {
			return connection;
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=utf-8", username,
					password);
			System.out.println("connected !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void disconnect() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
	
}
