package com.revature.models;

public class Employee
{
    /*
    id SERIAL PRIMARY KEY,
	job_title VARCHAR(100),
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	reports_to INTEGER REFERENCES employees(id)
    */

    int id;
    String jobTitle;
    String firstName, lastName;
    int reportsTo;
}