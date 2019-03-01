package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Credential;

public class CredentialDAO
{
	Connection connection = null;
	PreparedStatement stmt = null;
	
	public Credential getCred(String user, String password)
	{
		Credential cred = null;;
		try
		{
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM credentials WHERE user_name = ? AND password = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, user);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			int id = rs.getInt("user_id");
			cred = new Credential(id, user, password);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeResources();
		}
		return cred;
	}
	
	private void closeResources()
	{
		try
		{
			if (stmt != null)
				stmt.close();
		} catch (SQLException e)
		{
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try
		{
			if (connection != null)
				connection.close();
		} catch (SQLException e)
		{
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
}
