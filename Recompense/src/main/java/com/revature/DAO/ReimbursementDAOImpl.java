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
		return getAllRequests("SELECT * FROM requests");
	}
	public List<Reimbursement> getAllRequests(String sql)
	{
		List<Reimbursement> requests = new ArrayList<Reimbursement>();
		try
		{
			String base = "SELECT * FROM requests ";
			connection = DAOUtilities.getConnection();
			stmt = connection.prepareStatement(base+sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				int requestId = rs.getInt("id");
				double amount = rs.getDouble("amount");
				int employee = rs.getInt("employee_id");
				String status = rs.getString("status");// != null ? rs.getString("status") : null;
				byte[] data = rs.getBytes("image");// != null ? rs.getBytes("image") : null;
				Date date = Date.valueOf(rs.getString("date"));
				String desc = rs.getString("description");
				Reimbursement request = new Reimbursement(requestId, amount, employee, status, data, date, desc);
				requests.add(request);
			}

			rs.close();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeResources();
		}
		return requests;
	}
	public List<Reimbursement> getAllRequestsByEmployee(int employeeId)
	{
		String sql = String.format("WHERE employee_id = %d", employeeId);;

		return getAllRequests(sql);
	}

	public List<Reimbursement> getAllRequestsByStatus(String status)
	{
		String sql = String.format("WHERE status = '%s'", status);;

		return getAllRequests(sql);
	}

	public Reimbursement getRequestById(int id)
	{
		Reimbursement request = null;;
		try
		{
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM requests WHERE id = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			rs.next();
			int requestId = rs.getInt("id");
			double amount = rs.getDouble("amount");
			int employee = rs.getInt("employee_id");
			String status = rs.getString("status");// != null ? rs.getString("status") : null;
			byte[] data = rs.getBytes("image");// != null ? rs.getBytes("image") : null;
			Date date = Date.valueOf(rs.getString("date"));
			String desc = rs.getString("description");
			request = new Reimbursement(requestId, amount, employee, status, data, date, desc);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeResources();
		}
		return request;
	}

	public boolean addRequest(Reimbursement r)
	{
		try
		{
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO requests (amount, employee_id, description) VALUES (?, ?, ?)";
			stmt = connection.prepareStatement(sql);

//			stmt.setInt(1, r.getId());
			stmt.setDouble(1, r.getAmount());
			stmt.setInt(2, r.getEmployeeId());
			stmt.setString(3, r.getDescription());

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
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
