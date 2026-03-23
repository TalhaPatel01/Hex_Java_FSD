package com.hibernate.controller;

import com.hibernate.config.ProjConfig;
import com.hibernate.model.Booking;
import com.hibernate.service.BookingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BookingController {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        BookingService bookingService = context.getBean(BookingService.class);
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("1. Insert Booking");
            System.out.println("2. Fetch Booking with flight details based on given date");
            System.out.println("3. Update Booking dynamically");
            System.out.println("4. Total amount for flight on given date");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            sc.nextLine();
            if(input==0){
                break;
            }

            switch (input){
                case 1:
                    Booking booking = new Booking();
                    System.out.println("Enter Passenger Name");
                    String name = sc.nextLine();
                    booking.setPassengerName(name);

                    System.out.println("Enter Seat Number");
                    String sNumber = sc.next();
                    booking.setSeatNumber(sNumber);

                    System.out.println("Enter fare");
                    BigDecimal fare = sc.nextBigDecimal();
                    booking.setFare(fare);

                    System.out.println("Enter age");
                    int age = sc.nextInt();
                    booking.setAge(age);

                    System.out.println("Enter Flight Id:");
                    int flightId = sc.nextInt();

                    bookingService.insert(booking,flightId);
                    System.out.println("Booking inserted..");
                    break;

                case 2:
                    System.out.println("Enter date of booking");
                    String date = sc.next();
                    List<Booking> list = bookingService.fetchBookingByDate(date);
                    list.forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Enter Id to update");
                    int id = sc.nextInt();

                    Booking booking1 = new Booking();
                    booking1.setPassengerName("Harry Barren");

                    Booking booking2 = new Booking();
                    booking2.setPassengerName("harry");
                    booking2.setSeatNumber("W43");

                    bookingService.updateBooking(booking2,id);
                    System.out.println("Booking Updated successfully");
                    break;

                case 4:
                    System.out.println("Enter date to find sum for flight");
                    String sumDate = sc.next();
                    List<Object[]> sumList = bookingService.getSumOnDate(sumDate);

                    for(Object[] row : sumList){
                        int sumFlightId = (Integer) row[0];
                        BigDecimal totalFare = (BigDecimal) row[1];
                        System.out.println("Flight ID: "+sumFlightId+"| Total Fare: "+totalFare);
                    }
            }
        }
    }
}
