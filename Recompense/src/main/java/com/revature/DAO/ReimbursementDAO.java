package com.revature.DAO;

import java.util.List;

import com.revature.models.Reimbursement;
public interface ReimbursementDAO
{
	public List<Reimbursement> getAllRequests();
	public List<Reimbursement> getAllRequestsByEmployee(int employeeId);
	public List<Reimbursement> getAllRequestsByStatus(String status);
	public Reimbursement getRequestById(int id);
	
	public boolean addRequest(Reimbursement r);
	public boolean updateRequest(int requestId, int managerId, String status);
	public boolean deleteRequestById(int id);
}
