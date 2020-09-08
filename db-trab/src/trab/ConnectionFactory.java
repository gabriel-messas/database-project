package trab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/"
					+ "trab?useTimezone=true&serverTimezone=America/"
					+ "Sao_Paulo", 
					"admin", "admin");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
