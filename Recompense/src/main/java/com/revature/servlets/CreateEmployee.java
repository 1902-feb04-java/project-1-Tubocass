package com.revature.servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.DAOUtilities;
import com.revature.DAO.EmployeeDAO;
import com.revature.models.Employee;

/**
 * Servlet implementation class CreateEmployee
 */
@WebServlet("/employee_new")
public class CreateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO empDAO = DAOUtilities.getEmployeeDAO();
		String job = request.getParameter("employee_job_title");
		String fName = request.getParameter("employee_first_name");
		String lName = request.getParameter("employee_last_name");
		int reportsTo = Integer.parseInt(request.getParameter("manager_id"));
		boolean isBoss = request.getParameter("manager_status").equalsIgnoreCase("on")? true:false;
		System.out.println( isBoss);
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
