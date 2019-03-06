package com.revature.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.DAO.DAOUtil;
import com.revature.DAO.ReimbursementDAO;
import com.revature.models.Reimbursement;

@WebServlet("/create_requests")
@MultipartConfig
public class CreateRequests extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int employeeId = -1;
		employeeId = (int) request.getSession(false).getAttribute("userId");
		ReimbursementDAO reDAO = DAOUtil.getReimburseDAO();
		Double amount = Double.valueOf(request.getParameter("request_amount"));
		String desc = request.getParameter("request_description");
		Reimbursement imburse = new Reimbursement(amount, employeeId, desc);
		Part imageData = request.getPart("image");
		InputStream inStream = null;
		ByteArrayOutputStream outStream = null;
		
		try {
			inStream = imageData.getInputStream();
			outStream = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[1024];
			
			while(inStream.read(buffer) != -1)
			{
				outStream.write(buffer);
			}
			imburse.setImage(outStream.toByteArray());
			
		}catch(IOException exception) {
			System.out.println("Fail!");
			exception.printStackTrace();
		}
		
		if(reDAO.addRequest(imburse))
		{	
			response.getWriter().write("Added Successfully");
			System.out.println("Added Successfully");
		}else  {
			response.getWriter().write("Request was not added");
			System.out.println("FAILURE");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getWriter().write("Get Heard");
//		response.sendRedirect("requests_new.html");
	}
}
