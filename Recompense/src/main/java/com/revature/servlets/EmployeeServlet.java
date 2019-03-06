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

@WebServlet("/pull_employees")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String employee = request.getParameter("who");
		
		EmployeeDAO empDAO = DAOUtil.getEmployeeDAO();
		List<Employee> employees = empDAO.getAllEmployees();
		
		String json = new Gson().toJson(employees);
		response.getWriter().write(json);
	}

}
