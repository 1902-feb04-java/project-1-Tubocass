package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.DAO.DAOUtil;
import com.revature.DAO.EmployeeDAO;
import com.revature.models.Employee;

@WebServlet({"/employee_crud","/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected Employee ParseEmployee(HttpServletRequest request)
	{
		String job = request.getParameter("employee_job_title");
		String fName = request.getParameter("employee_first_name");
		String lName = request.getParameter("employee_last_name");
		int reportsTo = Integer.parseInt(request.getParameter("manager_id"));
		boolean isBoss = request.getParameter("manager_status") != null? true:false;
		Employee employee = new Employee(-1, job, fName, lName, reportsTo, isBoss);
		return employee;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String crud = request.getParameter("crud");
		EmployeeDAO empDAO = DAOUtil.getEmployeeDAO();
		Employee emp;
		
		switch(crud)
		{
			case "create":
			{
				emp = this.ParseEmployee(request);
				if(empDAO.addEmployee(emp))
				{	
					response.getWriter().write("Added Successfully");
					System.out.println("Added Successfully");
				}else  {
					response.getWriter().write("Employee was not added");
					System.out.println("FAILURE");
				}
				break;
			}
			case "read":
			{
				String who = request.getParameter("who");
				if(who.equals("all")) 
				{
					List<Employee> employees = empDAO.getAllEmployees();
					
					String json = new Gson().toJson(employees);
					response.getWriter().write(json);
				}else {
					emp = empDAO.getEmployeeById(Integer.parseInt(who));
					String json = new Gson().toJson(emp);
					response.getWriter().write(json);
				}
				
				break;
			}
			case "update":
			{
				emp = this.ParseEmployee(request);
				if(empDAO.updateEmployee(emp))
				{	
					response.getWriter().write("Updated Successfully");
					System.out.println("Updated Successfully");
				}else  {
					response.getWriter().write("Employee was not updated");
					System.out.println("FAILURE");
				}
				break;
			}
			case "delete":
			{
				break;
			}
		}
	}

}
