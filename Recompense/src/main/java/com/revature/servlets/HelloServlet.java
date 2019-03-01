package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/hey")
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
		
//		CredentialDAO credDAO = new CredentialDAO();
//		Credential cred = credDAO.getCred(user, password);
//		response.setContentType("plain/text");
//		response.setCharacterEncoding("UTF-8");
		BufferedReader buffedRead = request.getReader();
		StringBuilder builder = new StringBuilder();
		String line;
//		Gson g = new Gson();
//		Object o = new Object();
//		g.fromJson(buffedRead, o.getClass());
	    while ((line = buffedRead.readLine()) != null) 
	    {
	        builder.append(line);
	    }
	    String data = builder.toString();
		
		PrintWriter pr = response.getWriter();
		pr.write("<h1>"+ data +"</h1>");
		pr.close();
		pr.flush();
//		request.getRequestDispatcher("login.html").forward(request, response);
//		session.invalidate();
		
		
	}
}
