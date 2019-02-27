package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.DAOUtilities;
import com.revature.DAO.ReimbursementDAO;
import com.revature.models.Reimbursement;

@WebServlet("/create_requests")
public class CreateRequests extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println(request.getParameter("request_amount"));
		ReimbursementDAO reDAO = DAOUtilities.getReimburseDAO();
		Double amount = Double.valueOf(request.getParameter("request_amount"));
		int employeeId = Integer.valueOf(request.getParameter("employee_id"));
		String status = request.getParameter("request_status");
		if(reDAO.addRequest(new Reimbursement(amount, employeeId)))
		{
			request.setAttribute("message", "Request added");
			request.getRequestDispatcher("/requests_new.html").forward(request, response);
			System.out.println("Added Successfully");
		}else  {
			request.setAttribute("message", "Request was not added");
			request.getRequestDispatcher("/requests_new.html").forward(request, response);
			System.out.println("FAILURE");
		}
	}
}
