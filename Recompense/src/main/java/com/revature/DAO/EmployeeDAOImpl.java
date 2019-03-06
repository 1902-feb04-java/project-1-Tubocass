package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public class EmployeeDAOImpl implements EmployeeDAO
{
	Connection connection = null;
	PreparedStatement stmt = null;

	@Override
	public boolean isManager(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() 
	{
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			String base = "SELECT * FROM employees ";
			connection = DAOUtilities.getConnection();
			stmt = connection.prepareStatement(base);
			
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
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		
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
