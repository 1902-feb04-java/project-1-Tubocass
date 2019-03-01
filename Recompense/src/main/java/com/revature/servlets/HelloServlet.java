package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.CredentialDAO;
import com.revature.models.Credential;

@WebServlet("/login")
public class HelloServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		HttpSession session = request.getSession();
//		String user = (String) session.getAttribute("user");
//		if(user == null)
//		{
//			user = request.getParameter("user");
//			session.setAttribute("user", user);
//		}
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		CredentialDAO credDAO = new CredentialDAO();
		if(credDAO.tryLogin(user, password))
		{
			response.sendRedirect("home.html");
		}else {
			response.getWriter().write("failure");
		}
	}
//		request.getRequestDispatcher("login.html").forward(request, response);		
}
