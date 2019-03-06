package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.DAOUtil;
import com.revature.DAO.EmployeeDAO;
import com.revature.models.Employee;

@WebServlet("/update_employee")
public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO empDAO = DAOUtil.getEmployeeDAO();
		int id =  Integer.parseInt(request.getParameter("id"));
		String job = request.getParameter("employee_job_title");
		String fName = request.getParameter("employee_first_name");
		String lName = request.getParameter("employee_last_name");
		int reportsTo = Integer.parseInt(request.getParameter("manager_id"));
		boolean isBoss = request.getParameter("manager_status").equalsIgnoreCase("on")? true:false;
	
		Employee employee = new Employee(-1, job, fName, lName, reportsTo, isBoss);
		
		if(empDAO.addEmployee(employee))
		{	
			response.getWriter().write("Added Successfully");
			System.out.println("Added Successfully");
		}else  {
			response.getWriter().write("Employee was not added");
			System.out.println("FAILURE");
		}
	}

}
