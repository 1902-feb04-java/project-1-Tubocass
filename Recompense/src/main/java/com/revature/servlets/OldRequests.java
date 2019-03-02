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
import com.revature.DAO.DAOUtilities;
import com.revature.DAO.ReimbursementDAO;
import com.revature.models.Reimbursement;

/**
 * Servlet implementation class OldRequests
 */
@WebServlet("/past_requests")
public class OldRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ReimbursementDAO reqDAO = DAOUtilities.getReimburseDAO();
		List<Reimbursement> requests = new ArrayList<Reimbursement>();
		requests = reqDAO.getAllRequests();
		String json = new Gson().toJson(requests);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
