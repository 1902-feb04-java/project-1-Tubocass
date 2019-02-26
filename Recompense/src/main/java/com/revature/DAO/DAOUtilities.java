package com.revature.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used to retrieve DAO Implementations. Serves as a factory. Also manages a single instance of the database connection.
 */
public class DAOUtilities 
{

	private static final String CONNECTION_USERNAME = "postgres";
	private static final String CONNECTION_PASSWORD = "d3athnot3";
	private static final String URL = "jdbc:postgresql://localhost:5432/ProjectOne";
	private static Connection connection;
	
	public static synchronized Connection getConnection() throws SQLException 
	{
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
	
	public static ReimbursementDAO getReimburseDAO() 
	{
		return new ReimbursementDAOImpl();
	}
	public static EmployeeDAO getEmployeeDAO() 
	{
		return new EmployeeDAOImpl();
	}

}
