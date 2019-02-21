package com.revature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try(
            InputStreamReader readIn = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(readIn);

        ){
            System.out.println("Enter your name and expensed amount");
            String input = br.readLine();
            Employee emp = new Employee(input);
           
        }catch(IOException exception){
            exception.printStackTrace();
        }

    }
    
}
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

