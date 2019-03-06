package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAO.DAOUtil;
import com.revature.DAO.ReimbursementDAO;

@WebServlet("/update_request")
public class UpdateRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbursementDAO reDAO = DAOUtil.getReimburseDAO();
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			int userId = (int) session.getAttribute("userId");
			int reqId = Integer.parseInt(request.getParameter("id"));
			String status = request.getParameter("status");
			response.getWriter().write(""+reDAO.updateRequest(reqId, userId, status));
		}else response.sendRedirect("html/login.html");
	}

}
