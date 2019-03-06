package com.revature.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public class EmployeeDAOImpl extends CommonDAO implements EmployeeDAO
{
	@Override
	public Employee getEmployeeById(int id) 
	{
		String sql = String.format("WHERE id = %d", id);
		return getAllEmployees(sql).get(0);
	}
 
	@Override
	public Employee getEmployeeByName(String fName, String lName) 
	{
		String fn = "'"+fName +"'", ln = "'"+lName +"'"; 
		String sql = String.format("WHERE first_name = %s AND last_name = %s", fn, ln);
		return getAllEmployees(sql).get(0);
	}
	@Override
	public List<Employee> getAllEmployees() 
	{	
		return getAllEmployees("");
	}

	public List<Employee> getAllEmployees(String sql) 
	{
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			String base = "SELECT * FROM employees ";
			connection = DAOUtil.getConnection();
			stmt = connection.prepareStatement(base+sql);
			
			ResultSet rs = stmt.executeQuery();
			//id, job_title, first_name, last_name, reports_to, ismanager
			while(rs.next())
			{
				int empId = rs.getInt("id");
				String job = rs.getString("job_title");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				int myBoss = rs.getInt("reports_to");
				boolean isBoss = rs.getBoolean("ismanager");
				
				Employee emp = new Employee(empId, job, fName, lName, myBoss, isBoss);
				employees.add(emp);
			}
			rs.close();
		}catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeResources();
		}
		
		return employees;
	}
	@Override
	public boolean updateEmployee(Employee e) {
		try
		{
			//id, job_title, first_name, last_name, reports_to, ismanager
			connection = DAOUtil.getConnection();
			String sql = "UPDATE employees SET job_title = ? , first_name = ?, last_name = ?, reports_to = ?, ismanager = ?"
			+"WHERE id = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, e.getTitle());
			stmt.setString(2, e.getFirstName());
			stmt.setString(3, e.getLastName());
			stmt.setInt(4, e.getManagerId());
			stmt.setBoolean(5, e.IsAManager());
			stmt.setInt(6, e.getId());

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		} finally
		{
			closeResources();
		}
	}

	@Override
	public boolean addEmployee(Employee e) 
	{
		//id, job_title, first_name, last_name, reports_to, ismanager
		try {
			connection = DAOUtil.getConnection();
			String sql = "INSERT INTO employees (job_title, first_name, last_name, reports_to, ismanager)"
					+"VALUES(?,?,?,?,?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, e.getTitle());
			stmt.setString(2, e.getFirstName());
			stmt.setString(3, e.getLastName());
			stmt.setInt(4, e.getManagerId());
			stmt.setBoolean(5, e.IsAManager());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		} finally
		{
			closeResources();
		}
	}

	@Override
	public boolean deleteEmployee(int id) {
		return false;
		// TODO Auto-generated method stub
		
	}
}
