package com.revature.DAO;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO
{
	public boolean isManager(int id);
	public Employee getEmployeeById(int id);
	public List<Employee> getAllEmployees();
	public void updateEmployee(Employee employee);
	public void addEmployee(Employee employee);
	public void deleteEmployee(int id);
}
