package trab;
 
import java.sql.*;
 
public class UserDAO {
	
private Connection connection;
	
	public UserDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
 
    public User checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        
    	String sql = "SELECT * FROM user WHERE USERNAME = ? and PASSWORD = ?";
    	
    	User user = null;
    	
    	try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, username);
	        statement.setString(2, password);
			
	        ResultSet result = statement.executeQuery();
	        
	        
	        
	        if (result.next()) {
	            user = new User();
	            user.setUsername(username);
	            user.setPassword(password);
	        }
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
 
        return user;
    }

	public boolean register(String username, String password) {
		
		String sql = "INSERT INTO user (USERNAME, PASSWORD) VALUES (?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, username);
	        statement.setString(2, password);
			
			statement.execute();
			
			statement.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
        return true;
	}
}