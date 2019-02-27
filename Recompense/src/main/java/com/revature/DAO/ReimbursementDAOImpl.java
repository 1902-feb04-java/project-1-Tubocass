package com.revature.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;

public class ReimbursementDAOImpl implements ReimbursementDAO
{
	Connection connection = null;
	PreparedStatement stmt = null;
	
	public List<Reimbursement> getAllRequests()
	{
		List<Reimbursement> requests = new ArrayList<Reimbursement>();
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM requests";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Book object with info for each row from our query result

				int requestId = rs.getInt("id");
				double amount = rs.getDouble("amount");
				int employee = rs.getInt("employee_id");
				String status = rs.getString("status") != null?  rs.getString("status"):null;
				byte[] data = rs.getBytes("image") != null?  rs.getBytes("image"):null;
				Reimbursement request = new Reimbursement(requestId, amount, employee, status, data);
				requests.add(request);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		return requests;
	}

	
	public List<Reimbursement> getAllRequestsByEmployee(int employeeId)
	{
		List<Reimbursement> requests = new ArrayList<Reimbursement>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM requests WHERE employee_id = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, employeeId);
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			while (rs.next()) 
			{
				int requestId = rs.getInt("id");
				double amount = rs.getDouble("amount");
				int employee = rs.getInt("employee_id");
				String status = rs.getString("status") != null?  rs.getString("status"):null;
				byte[] data = rs.getBytes("image") != null?  rs.getBytes("image"):null;
				Reimbursement request = new Reimbursement(requestId, amount, employee, status, data);
				requests.add(request);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return requests;
	}

	
	public List<Reimbursement> getAllRequestsPending()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public Reimbursement getRequestById(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean addRequest(Reimbursement r)
	{
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO requests (amount, employee_id, status) VALUES (?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			
//			stmt.setInt(1, r.getId());
			stmt.setDouble(1,r.getAmount()); // CAST ('10.2' AS DOUBLE PRECISION)
			stmt.setInt(2, r.getEmployeeId());
			stmt.setString(3, "pending");
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	
	public boolean updateRequest(Reimbursement r)
	{
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean deleteRequestById(int id)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
