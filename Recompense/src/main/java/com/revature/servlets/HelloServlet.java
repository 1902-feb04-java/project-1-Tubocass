package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAO.CredentialDAO;

@WebServlet("/login")
public class HelloServlet extends HttpServlet
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
		}
//		user = request.getParameter("user");
		String password = request.getParameter("password");
		
		CredentialDAO credDAO = new CredentialDAO();
		int empId = credDAO.tryLogin(user, password);
		if(empId>0)
		{
			session.setAttribute("user", user);
			session.setAttribute("userId", empId);
			response.sendRedirect("html/home.html");
		}else {
			response.sendRedirect("html/login.html");
			response.getWriter().write("failure");
		}
	}
//		request.getRequestDispatcher("login.html").forward(request, response);		
}
