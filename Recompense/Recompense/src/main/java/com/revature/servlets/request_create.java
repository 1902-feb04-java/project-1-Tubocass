package com.revature.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.DAO.DAOUtilities;
import com.revature.DAO.ReimbursementDAO;
import com.revature.models.Reimbursement;

@WebServlet("/request_create")
public class request_create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public request_create() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int reqId = Integer.parseInt(request.getParameter("requestId"));
//		doGet(request, response);
		ReimbursementDAO reDAO = DAOUtilities.getReimburseDAO();
		Reimbursement imburse = reDAO.getRequestById(reqId);
		
		if(imburse != null)
		{
			request.getSession().setAttribute("message", "Reimbursement with id "+ reqId +" already exists");
//			Send back to homepage.
//			request.getRequestDispatcher("Create Reimbursement").forward(request, response);
		}else {
			int id = reqId;
			double amount = Double.parseDouble(request.getParameter("amount"));
			int empId = Integer.parseInt(request.getParameter("employee_id"));
			String status = request.getParameter("status");
			Part imageContent = request.getPart("image_data");
			imburse = new Reimbursement(id, amount, empId, status);
			
			InputStream is = null;
			ByteArrayOutputStream os = null;

			try {
				is = imageContent.getInputStream();
				os = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];

				while (is.read(buffer) != -1) {
					os.write(buffer);
				}
				
				imburse.setImage(os.toByteArray());

			} catch (IOException e) {
				System.out.println("Could not upload file!");
				e.printStackTrace();
			} finally {
				if (is != null)
					is.close();
				if (os != null)
					os.close();
			}
		}
	}

}