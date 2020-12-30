package singleton_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	//1. Create static instance
	private static DatabaseConnection databaseConnection;	
	//create private member
	private Connection connection;
	
	private String url = "jdbc:postgresql://localhost:5432/jdbc";
    private String username = "root";
    private String password = "localhost";
	
    // create private member
    private Connection getConnection() {
		return connection;
	}

	private DatabaseConnection() throws SQLException {
		try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
	}
    
    public static DatabaseConnection getInstance() throws SQLException {
    	if(databaseConnection == null) {
    		databaseConnection=new DatabaseConnection();
    	}
    	else if(databaseConnection.getConnection().isClosed()) {
    		databaseConnection= new DatabaseConnection();
    	}
		return databaseConnection;
    }

}
