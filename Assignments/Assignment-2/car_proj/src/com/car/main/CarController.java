package com.car.main;

import java.sql.*;
import java.util.*;

import com.car.model.Car;
import com.car.service.CarService;

public class CarController {
    public static void main(String[] args){
        try (Scanner sc = new Scanner(System.in)) {
            CarService carService = new CarService();
            
            while(true){
                System.out.println("1. Show Car Info");
                System.out.println("2. Show Car Stats");
                System.out.println("3. Exit");
                int choice = sc.nextInt();
                if(choice==3){
                    break;
                }
                
                switch(choice){
                    case 1 -> {
                        try{
                            List<Car> list = carService.getCarInfo();
                            list.forEach(car->{
                                System.out.println("Registration Number: "+car.getRegistrationNumber());
                                System.out.println("Brand: "+car.getBrand());
                                System.out.println("Model: "+car.getModel());
                                System.out.println("Owner Name: "+car.getOwner().getName());
                                System.out.println("Year of Purchase: "+car.getCarDetails().getYear());
                                System.out.println("Milage: "+car.getCarDetails().getMilage());
                                System.out.println();
                            });
                        }
                        catch(SQLException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    
                    case 2 -> {
                        try{
                            Map<String,Integer> map = carService.getCarStats();
                            System.out.printf("%-20s %10s\n", "Brand Name", "No of Cars");
                            for(Map.Entry<String,Integer> entry : map.entrySet()){
                                System.out.printf("%-20s %5d\n", entry.getKey(), entry.getValue());
                            }
                        }
                        catch(SQLException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    
                    default -> System.out.println("Incorrect Choice");
                }
            }
        }
    }
}