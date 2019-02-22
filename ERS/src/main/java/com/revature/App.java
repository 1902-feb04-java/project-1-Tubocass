package com.revature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.revature.models.Employee;

public class App 
{
    public static void main( String[] args )
    {
        try(
            InputStreamReader readIn = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(readIn);

        ){
            // StringBuilder emp = new StringBuilder();
            String jobString, fName, lName;
            int id, manId;
            System.out.println("Enter your id: ");
            id = Integer.parseInt(br.readLine());
            System.out.println("Enter your name: ");
            Scanner scan = new Scanner(br.readLine());
            fName = scan.next();
            lName = scan.next();
            // emp.append(br.readLine());
            System.out.println("Enter your job title: ");
            jobString = br.readLine();
            // emp.append(br.readLine());
            System.out.println("Enter your manager's id: ");
            manId = Integer.parseInt(br.readLine());
            // emp.append(br.readLine());

            Employee employee = new Employee(id, jobString, fName, lName, manId);
           
        }catch(IOException exception){
            exception.printStackTrace();
        }

    }
    
}
/* initial outline
class Employee{
    String name, amount;
    Employee(String line)
    {
        Scanner scan = new Scanner(line).useDelimiter(", ");

        name = "name: "+scan.next();
        amount = "amount: "+scan.next();
        scan.close();
        System.out.println(name +" - "+ amount);
    }
}
*/

