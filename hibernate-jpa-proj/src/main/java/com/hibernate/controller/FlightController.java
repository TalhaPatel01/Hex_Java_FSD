package com.hibernate.controller;

import com.hibernate.config.ProjConfig;
import com.hibernate.model.Airline;
import com.hibernate.model.Flight;
import com.hibernate.service.FlightService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

                case 2:
                    List<?> list = flightService.getAllAirlines();
                    list.forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Enter airline id to add flight: ");
                    int airlineId = sc.nextInt();
                    sc.nextLine();

                    try{
                        Airline airline1 = flightService.getAirlineById(airlineId);

                        Flight flight = new Flight();
                        System.out.println("Enter Flight Number:");
                        flight.setFlightNumber(sc.nextLine());
                        System.out.println("Enter Source:");
                        flight.setSource(sc.nextLine());
                        System.out.println("Enter Destination:");
                        flight.setDestination(sc.nextLine());
                        System.out.println("Enter Departure Time:");
                        String departureTime = sc.nextLine();
                        LocalTime time = LocalTime.parse(departureTime,DateTimeFormatter.ISO_LOCAL_TIME);
                        flight.setDepartureTime(time);
                        flight.setAirline(airline1);

                        flightService.insertFlight(flight);
                        System.out.println("Flight added Successfully");
                    }
                    catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    List<?> list1 = flightService.getAllFlights();
                    list1.forEach(System.out::println);
                    break;
            }
        }
        sc.close();
        context.close();
    }
}