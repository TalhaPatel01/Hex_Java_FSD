package com.car.service;

import com.car.model.Car;
import com.car.repository.CarRepository;

import java.util.*;
import java.sql.*;

public class CarService {
    CarRepository carRepository = new CarRepository();

    public List<Car> getCarInfo() throws SQLException{
        return carRepository.getCarInfo();
    }
    
    public Map<String, Integer> getCarStats() throws SQLException{
        return carRepository.getCarStats();
    }
}