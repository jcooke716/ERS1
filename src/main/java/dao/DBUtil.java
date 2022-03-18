package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	static Connection conn;
	static {
		// establishing connection to postgres database
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static Connection getConnection() {
		// initializing singular instance of connection object
		    
		       
		String connectionUrl = "jdbc:postgresql://ip-172-31-28-50.ec2.internal:8888/project1";
		String username = "postgres";
		String password = "mysecretpassword";
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(connectionUrl, username,
						password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
		
	}
	
	static void closeConnection() {
		// severing connection to the database
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
