package com.revature.DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;

public class ReimbursementDAOImpl extends CommonDAO implements ReimbursementDAO
{
	public List<Reimbursement> getAllRequests()
	{
		return getAllRequests("");
	}
	protected List<Reimbursement> getAllRequests(String sql)
	{
		List<Reimbursement> requests = new ArrayList<Reimbursement>();
		try
		{
			String base = "SELECT * FROM requests ";
			connection = DAOUtil.getConnection();
			stmt = connection.prepareStatement(base+sql);

			ResultSet rs = stmt.executeQuery();

			requests = this.ParseReimbursement(rs);

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
		String sql = String.format("WHERE employee_id = %d", employeeId);

		return getAllRequests(sql);
	}

	public List<Reimbursement> getAllRequestsByStatus(String status)
	{
		String sql = String.format("WHERE status = '%s'", status);

		return getAllRequests(sql);
	}

	public Reimbursement getRequestById(int id)
	{
		String sql = String.format("WHERE id = %d", id);
	
		return  this.getAllRequests(sql).get(0);
	}

	public boolean addRequest(Reimbursement r)
	{
		try
		{
			connection = DAOUtil.getConnection();
			String sql = "INSERT INTO requests (amount, employee_id, description, image) VALUES (?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);

//			stmt.setInt(1, r.getId());
			stmt.setDouble(1, r.getAmount());
			stmt.setInt(2, r.getEmployeeId());
			stmt.setString(3, r.getDescription());
			stmt.setBytes(4, r.getImage());

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

	public boolean updateRequest(int requestId, int managerId, String status)
	{
		try
		{
			connection = DAOUtil.getConnection();
			String sql = "UPDATE requests SET status = ? , finalized_by = ?"
					+"WHERE id = ?";
			stmt = connection.prepareStatement(sql);

//			status = "'"+status+"'";
			stmt.setString(1, status);
			stmt.setInt(2, managerId);
			stmt.setInt(3, requestId);

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

	public boolean deleteRequestById(int id)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	List<Reimbursement> ParseReimbursement(ResultSet rs)
	{
		List<Reimbursement> requests = null;
		try
		{
			requests = new ArrayList<Reimbursement>();
			while (rs.next())
			{
				int requestId = rs.getInt("id");
				double amount = rs.getDouble("amount");
				int employee = rs.getInt("employee_id");
				String status = rs.getString("status");// != null ? rs.getString("status") : null;
				byte[] image = rs.getBytes("image");// != null ? rs.getBytes("image") : null;
				Date date = Date.valueOf(rs.getString("date"));
				String desc = rs.getString("description");
				int finisher = rs.getInt("finalized_by");
				Reimbursement request = new Reimbursement(requestId, amount, employee, status, image, date, desc, finisher);
				request.setImageString(image);
				requests.add(request);
			}
		}catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return requests;
	}
}
