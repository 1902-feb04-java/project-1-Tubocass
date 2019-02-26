package com.revature.DAO;

import java.util.List;

import com.revature.models.Reimbursement;

public class TestDAO
{
	public static void main(String[] args)
	{
		ReimbursementDAO reDAO = DAOUtilities.getReimburseDAO();
		
		List<Reimbursement> requests = reDAO.getAllRequests();
		
		for(int i =0;i<requests.size(); i++)
		{
			System.out.println( requests.get(i).toString());
		}
		
		boolean itWorked = reDAO.addRequest(new Reimbursement(42.00, 1, "pending"));
		System.out.println(itWorked);
	}
}
	

