package com.revature.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.revature.DAO.DAOUtil;
import com.revature.DAO.EmployeeDAO;
import com.revature.DAO.ReimbursementDAO;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;

@WebServlet("/reimbursement_crud")
@MultipartConfig // We're expecting data that's broken into multiple parts
public class ReimbursementServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected Reimbursement ParseReimbursement(HttpServletRequest request)
	{
		int employeeId = -1;
		employeeId = (int) request.getSession(false).getAttribute("userId");
		Double amount = Double.valueOf(request.getParameter("request_amount"));
		String desc = request.getParameter("request_description");
		Reimbursement imburse = new Reimbursement(amount, employeeId, desc);
		
		try {
			Part imageData = request.getPart("image");//binary data
			InputStream inStream = null;
			ByteArrayOutputStream outStream = null;
			
			inStream = imageData.getInputStream();
			outStream = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[1024];
			
			while(inStream.read(buffer) != -1) //read from the stream into the buffer until there's nothing left to read
			{
				outStream.write(buffer);
			}
			imburse.setImage(outStream.toByteArray()); // output data from buffer as byte array and set our reimbursement image to that
		}catch(IOException exception) {
			System.out.println("Fail!");
			exception.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}	
		
		return imburse;
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String crud = request.getParameter("crud");
		Reimbursement recompense;
		ReimbursementDAO reDAO = DAOUtil.getReimburseDAO();
		HttpSession session = request.getSession(false);
		//if(session != null)
		{
			switch(crud)
			{
				case "create":
				{
					recompense = this.ParseReimbursement(request);
					if(reDAO.addRequest(recompense)) // attempt to add request to SQL
					{	
						response.getWriter().write("Added Successfully");
						System.out.println("Added Successfully");
					}else  {
						response.getWriter().write("Request was not added");
						System.out.println("FAILURE");
					}
					break;
				}
				case "read":
				{
					String reqStatus = request.getParameter("status");
					String who = request.getParameter("who");
					//Add parameter to get all for a specific employee
					List<Reimbursement> requests = new ArrayList<Reimbursement>();
					System.out.println(who);
					if(who.equals("all")) 
					{
						requests = reDAO.getAllRequestsByStatus(reqStatus);
						
					}else if(who.equals("current"))
					{
						requests = reDAO.getAllRequestsByStatusAndEmployee((int) session.getAttribute("userId"),reqStatus);
					}else {
//						EmployeeDAO empDAO = DAOUtil.getEmployeeDAO();
//						Employee emp = empDAO.getEmployeeByLastName(who);
//						requests = reDAO.getAllRequestsByStatusAndEmployee(emp.getId(),reqStatus);
						requests = reDAO.getAllRequestsByStatusAndEmployee(Integer.parseInt(who),reqStatus);
					}
					String json = new Gson().toJson(requests);
					response.getWriter().write(json);
					break;
				}
				case "update":
				{	
					int userId = (int) session.getAttribute("userId");
					int reqId = Integer.parseInt(request.getParameter("id"));
					String status = request.getParameter("status");
					response.getWriter().write(""+reDAO.updateRequest(reqId, userId, status));
					break;
				}
				case "delete":
				{
					break;
				}
			}
		}//else response.sendRedirect("html/login.html");
	}
}
