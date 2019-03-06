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
	public List<Employee> getAllEmployees() 
	{	
		return getAllEmployees("");
	}

	public List<Employee> getAllEmployees(String sql) 
	{
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			String base = "SELECT * FROM employees ";
			connection = DAOUtilities.getConnection();
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
}
