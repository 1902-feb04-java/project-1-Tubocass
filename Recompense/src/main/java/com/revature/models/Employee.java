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
    int managerId;

    static int count = 0;

    public Employee()
    {
        this.id = -1;
        this.jobTitle = null;
        this.firstName = null;
        this.lastName = null;
        this.managerId = -1;
    }
    public Employee(int id, String job, String fName, String lName, int manId)
    {
        this.id = id;
        this.jobTitle = job;
        this.firstName = fName;
        this.lastName = lName;
        this.managerId = manId;
        System.out.println(this.toString());
    }
    @Override
    public String toString()
    {
        String info = "ID: "+ id+ ", Name: " +firstName+" "+ lastName+", Job: "+jobTitle+", Mananger ID: "+managerId;
        return info;
    }

    static int nextId()
    {
        return count++;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public void setManagerId(int manId)
    {
        this.managerId = manId;
    }
    public void setJobTitle(String title)
    {
        this.jobTitle = title;
    }
    public void setFirstName(String first)
    {
        this.firstName = first;
    }
    public void setLastName(String last)
    {
        this.lastName = last;
    }

    public int getId()
    {
        return this.id;
    }
    public int getManagerId()
    {
        return this.managerId;
    }

    public String getTitle()
    {
        return this.jobTitle;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
}