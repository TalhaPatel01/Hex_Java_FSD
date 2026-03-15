package com.car.dao;

import java.util.*;

import com.car.model.Car;

public interface CarDao {
    List<Car> getCarInfo();
    Map<String,Integer> getCarStats();
}
