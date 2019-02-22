package com.revature.models;

public class Reimbursement{
    /*
    id SERIAL PRIMARY KEY,
	amount MONEY NOT NULL,
	employee_id INTEGER REFERENCES employees(id) NOT NULL,
	status VARCHAR(50), --would prefer an enum type, e.pending, e.accepted, e.rejected
	image BYTEA
    */
    public enum Status{pending, accepted, rejected, invalid}
    int id;
    float amount;
    int employeeId;
    Status status;
    byte[] image;
    
	public int getId() {
		return id;
	}
	public float getAmount() {
		return amount;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public Status getStatus() {
		return status;
	}
	public byte[] getImage() {
		return image;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

}