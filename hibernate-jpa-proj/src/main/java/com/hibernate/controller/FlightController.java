package com.hibernate.controller;

import com.hibernate.config.ProjConfig;
import com.hibernate.model.Airline;
import com.hibernate.service.FlightService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.Scanner;

public class FlightController {
    public static void main(String[] args){
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        LocalContainerEntityManagerFactoryBean emf = context.getBean(LocalContainerEntityManagerFactoryBean.class);
        Scanner sc = new Scanner(System.in);
        FlightService flightService = context.getBean(FlightService.class);

        while (true){
            System.out.println("1. Insert Airline");
            System.out.println("2. Show all Airlines");
            System.out.println("3. Insert Flight");
            System.out.println("4. Show all Flights");
            System.out.println("5. Update Flight");
            System.out.println("6. Delete Flight by ID");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            sc.nextLine();
            if(input == 0)
                break;

            switch (input){
                case 1:
                    Airline airline = new Airline();

                    System.out.println("Enter Name: ");
                    String airlineName = sc.nextLine();
                    airline.setName(airlineName);
                    System.out.println("Enter Country: ");
                    String airlineCountry = sc.nextLine();
                    airline.setCountry(airlineCountry);
                    flightService.insert(airline);
                    System.out.println("Airline Inserted");
                    break;

                default:
                    System.out.println("Incorrect choice");
            }
        }

        sc.close();
        context.close();
    }
}