package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAO.CredentialDAO;
import com.revature.DAO.DAOUtil;
import com.revature.DAO.EmployeeDAO;
import com.revature.models.Employee;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		if(user == null)
		{
			user = request.getParameter("user");
		}else {
			String newUser = request.getParameter("user");
			if(!user.equals(newUser))
			{
//				session.invalidate();
				user = newUser;
			}
		}
		
		String password = request.getParameter("password");
		
		CredentialDAO credDAO = new CredentialDAO();
		EmployeeDAO empDAO = DAOUtil.getEmployeeDAO();
		int empId = credDAO.tryLogin(user, password);
		// is employee a manager?
		if(empId>0)
		{
			Employee employee = empDAO.getEmployeeById(empId);
			session.setAttribute("user", user);
			session.setAttribute("userId", empId);
			session.setAttribute("isManager", employee.IsAManager());
			System.out.println(empId);
			System.out.println( "user"+session.getAttribute("userId"));
			if(employee.IsAManager())
			{
				response.sendRedirect("html/manager_home.html");
			}else {
				System.out.println(employee.toString());
				response.sendRedirect("html/employee_home.html");
			}
		}else {
			response.sendRedirect("html/login.html");
			response.getWriter().write("failure");
		}
	}
//		request.getRequestDispatcher("login.html").forward(request, response);		
}
