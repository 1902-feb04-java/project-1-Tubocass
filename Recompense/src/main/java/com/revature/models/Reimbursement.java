package com.revature.models;

import java.sql.Date;

public class Reimbursement
{
	/*
	 * id SERIAL PRIMARY KEY, 
	 * amount NUMERIC NOT NULL, 
	 * employee_id INTEGER REFERENCES employees(id) NOT NULL
	 * --would prefer an enum type, e.pending, e.accepted, e.rejected image BYTEA
	 * status VARCHAR(50), 
	 * image BYTEA
	 * date DATE
	 * description VARCHAR(100)
	 */
	public enum Status
	{
		pending, accepted, rejected, invalid
	}

	int id;
	double amount;
	int employeeId;
	Status status;
	byte[] image;
	Date date;
	String description;
	
	public Reimbursement(int id, double money, int empId, String stat, byte[] imgData, Date date, String desc)
	{
		this.id = id;
		this.amount = money;
		this.employeeId = empId;
		this.status = stat != null? Status.valueOf(stat.toLowerCase()): null;
		this.date = date;
		this.description = desc;
		this.image = imgData;
	}
	public Reimbursement(double money, int empId, String desc)
	{
		this.amount = money;
		this.employeeId = empId;
		this.description = desc;
	}
	
	@Override
	public String toString()
	{
		return "Reimbursement [id=" + id + ", amount=" + amount + ", employeeId=" + employeeId + ", status=" + status
				+", date=" + date + ", description=" + description +", file size="+image+"]";
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
	
	public Date getDate() 
	{
		return date;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setStatus(Status status) 
	{
		this.status = status;
	}

	public void setImage(byte[] data)
	{
		this.image = data;	
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public void setDate(Date date) 
	{
		this.date = date;
	}
}