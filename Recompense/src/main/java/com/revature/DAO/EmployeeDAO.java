package com.revature.DAO;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO
{
	public Employee getEmployeeById(int id);
	public List<Employee> getAllEmployees();
	public boolean updateEmployee(Employee employee);
	public boolean addEmployee(Employee employee);
	public boolean deleteEmployee(int id);
}
