package com.hibernate.controller;

import com.hibernate.config.ProjConfig;
import com.hibernate.exception.ResourceNotFoundException;
import com.hibernate.model.Employee;
import com.hibernate.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class EmployeeController {
    public static void main(String[] args){
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        EmployeeService employeeService = context.getBean(EmployeeService.class);
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("1. Insert Employee");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            if (input == 0)
                break;

            switch (input){
                case 1:
                    Employee employee = new Employee();
                    System.out.println("Enter Employee Name: ");
                    employee.setName(sc.next());
                    System.out.println("Enter Employee Email");
                    employee.setEmail(sc.next());
                    System.out.println("Enter Employee job title: ");
                    String jobTitle = sc.next();
                    System.out.println("Enter Airline ID: ");
                    int airlineId = sc.nextInt();

                    try {
                        employeeService.insert(employee, jobTitle, airlineId);
                        System.out.println("Employee inserted successfully");
                    }
                    catch (IllegalArgumentException | ResourceNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}