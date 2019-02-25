package com.revature.DAO;

import java.util.List;

import com.revature.models.*;
public interface ReimbursementDAO
{
	public List<Reimbursement> getAllRequests();
	public List<Reimbursement> getAllRequestsByEmployee(int employeeId);
	public List<Reimbursement> getAllRequestsPending();
	public Reimbursement getRequestById();
	
	public boolean addRequest(Reimbursement r);
	public boolean updateRequest(Reimbursement r);
	public boolean deleteRequestById(int id);
}
