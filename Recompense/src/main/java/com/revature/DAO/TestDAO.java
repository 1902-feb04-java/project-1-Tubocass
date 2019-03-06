package com.revature.DAO;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public class TestDAO
{
	public static void main(String[] args)
	{
		ReimbursementDAO reDAO = DAOUtilities.getReimburseDAO();
		EmployeeDAO empDAO = DAOUtilities.getEmployeeDAO();
//		CredentialDAO credDAO = new CredentialDAO();
//		Credential creds = credDAO.getCred("BigBoi", "puppies");
//		List<Reimbursement> requests = reDAO.getAllRequestsByStatus("pending");
		List<Reimbursement> requests = reDAO.getAllRequests();
		Reimbursement req = reDAO.getRequestById(24);
		Employee emp = empDAO.getEmployeeByName("Zapp", "Brannigan");
		System.out.println(req.toString());
		System.out.println(emp.toString());
		System.out.println(reDAO.updateRequest(31, 2, "rejected"));
		for(int i =0;i<requests.size(); i++)
		{
			System.out.println( requests.get(i).toString());
		}
		
//		System.out.println(creds.getUserName());
//		System.out.println(credDAO.tryLogin("BigBoi", "puppies"));
		
//		boolean itWorked = reDAO.addRequest(new Reimbursement(45.00, 1, "pending"));
//		System.out.println(itWorked);
	}
}
	

