package com.hibernate.controller;

import com.hibernate.config.ProjConfig;
import com.hibernate.dto.FlightDto;
import com.hibernate.exception.ResourceNotFoundException;
import com.hibernate.model.Employee;
import com.hibernate.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    public static void main(String[] args){
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        EmployeeService employeeService = context.getBean(EmployeeService.class);
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("1. Insert Employee");
            System.out.println("2. Show All Employees");
            System.out.println("3. Fetch Employee and Airline Info by Flight");
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

                case 2:
                    List<?> list = employeeService.showAllEmployees();
                    list.forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Enter Flight Id: ");
                    int flightId = sc.nextInt();
                    FlightDto flightDto = employeeService.fetchEmployeeAndAirlineByFlight(flightId);
                    System.out.println(flightDto.flightNumber() + "\t\t" +  " Source: " + flightDto.source());
                    System.out.println(flightDto.airline_name() + "\t\t" +  " Destination: " + flightDto.destination());
                    flightDto.employees().forEach(System.out :: println);
                    break;
            }
        }
    }
}