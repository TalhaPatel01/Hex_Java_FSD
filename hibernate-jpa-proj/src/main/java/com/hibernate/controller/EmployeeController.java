package com.hibernate.controller;

import com.hibernate.config.ProjConfig;
import com.hibernate.dto.FlightDto;
import com.hibernate.enums.JobTitle;
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
            System.out.println("4. Fetch Employees by Job Title using Criteria Query");
            System.out.println("5. Delete Employee by id");
            System.out.println("6. Dynamic Update");

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

                case 4:
                    System.out.println("Enter Job Title:");
                    String jobTitle1 = sc.next();
                    try{
                        List<Employee> list1 = employeeService.fetchEmployeesByJobTitle(jobTitle1);
                        list1.forEach(System.out::println);
                    }
                    catch (IllegalArgumentException e){
                        System.out.println("Invalid Job Title..");
                    }
                    break;

                case 5:
                    System.out.println("Enter Employee id");
                    int employeeId = sc.nextInt();
                    employeeService.deleteEmployeeById(employeeId);
                    System.out.println("Employee Deleted..");
                    break;

                case 6:
                    System.out.println("Enter Id to update:");
                    int id = sc.nextInt();

                    Employee updateEmployee1 = new Employee();
                    updateEmployee1.setName("Harry Porter");

                    Employee updateEmployee2 = new Employee();
                    updateEmployee2.setName("harry porter");
                    updateEmployee2.setEmail("harry.porter@gmail.com");

                    Employee updateEmployee3 = new Employee();
                    updateEmployee3.setName("harry");
                    updateEmployee3.setEmail("harry_porter@gmail.com");
                    updateEmployee3.setJobTitle(JobTitle.CAPTAIN);

                    employeeService.updateEmployee(updateEmployee3,id);
                    System.out.println("Employee updated...");

                    break;
            }
        }
    }
}