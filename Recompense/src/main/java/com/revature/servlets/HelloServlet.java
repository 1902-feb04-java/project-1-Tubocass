package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAO.CredentialDAO;
import com.revature.models.Credential;

@WebServlet("/hey")
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
			session.setAttribute("user", user);
		}
		String password = request.getParameter("password");
		
		CredentialDAO credDAO = new CredentialDAO();
		Credential cred = credDAO.getCred(user, password);
		
		PrintWriter pr = response.getWriter();
		pr.println("<h1>Hey there "+user+"!</h1>");
		pr.close();
		
//		request.getRequestDispatcher("requests_new").forward(request, response);
		session.invalidate();
		
		
	}
}
