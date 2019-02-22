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

}