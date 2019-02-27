package com.revature.models;

public class Reimbursement
{
	/*
	 * id SERIAL PRIMARY KEY, amount MONEY NOT NULL, employee_id INTEGER REFERENCES
	 * employees(id) NOT NULL, status VARCHAR(50), --would prefer an enum type,
	 * e.pending, e.accepted, e.rejected image BYTEA
	 */
	public enum Status
	{
		pending, accepted, rejected, invalid
	}

	int id;
	double amount;
	int employeeId;
	Status status;
	//add date
	byte[] image;

	public Reimbursement(int id, double money, int empId, String stat)
	{
		this(id, money, empId, stat, null);
	}
	public Reimbursement(int id, double money, int empId, String stat, byte[] imgData)
	{
		this.id = id;
		this.amount = money;
		this.employeeId = empId;
		this.status = Status.valueOf(stat.toLowerCase());
		//add date
		this.image = imgData;
	}
	public Reimbursement(double money, int empId, String stat)
	{
		this.amount = money;
		this.employeeId = empId;
		this.status = Status.valueOf(stat);
	}
	public Reimbursement(Double amount, int employeeId)
	{
		this(amount, employeeId, "pending");
	}
	@Override
	public String toString()
	{
		return "Reimbursement [id=" + id + ", amount=" + amount + ", employeeId=" + employeeId + ", status=" + status
				+ "]";
	}
	public int getId()
	{
		return id;
	}

	public double getAmount()
	{
		return amount;
	}

	public int getEmployeeId()
	{
		return employeeId;
	}

	public String getStatus()
	{
		return status.toString();
	}

	public byte[] getImage()
	{
		return image;
	}
	public void setImage(byte[] data)
	{
		this.image = data;
		
	}
	

}