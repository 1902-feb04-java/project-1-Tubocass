package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.DAO.DAOUtil;
import com.revature.DAO.ReimbursementDAO;
import com.revature.models.Reimbursement;

@WebServlet("/past_requests")
public class GetRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String reqStatus = request.getParameter("status");
		
		ReimbursementDAO reqDAO = DAOUtil.getReimburseDAO();
		List<Reimbursement> requests = new ArrayList<Reimbursement>();
		requests = reqDAO.getAllRequestsByStatus(reqStatus);
		
		String json = new Gson().toJson(requests);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
