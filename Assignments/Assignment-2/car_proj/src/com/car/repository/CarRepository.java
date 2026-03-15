package com.car.repository;

import java.sql.*;
import java.util.*;

import com.car.dao.CarDao;
import com.car.model.Car;
import com.car.model.CarDetails;
import com.car.model.Owner;
import com.car.utility.DBConnection;

public class CarRepository implements CarDao{
    @Override
    public List<Car> getCarInfo(){
        List<Car> list = new ArrayList<>();
        DBConnection dbConnection  = DBConnection.getInstance();

        try{
            Connection conn = dbConnection.dbConnect();
            String sql = "select c.registration_number, c.brand, c.model, o.name as owner_name, cd.year_of_purchase, cd.milage " +
                                "from owner o join car c on o.id = c.owner_id " +
                                "join car_details cd on c.car_details_id = cd.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Car car = new Car();
                String rNumber = rs.getString("registration_number");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                car.setRegistrationNumber(rNumber);
                car.setBrand(brand);
                car.setModel(model);

                Owner owner = new Owner();
                String oName = rs.getString("owner_name");
                owner.setName(oName);

                CarDetails carDetails = new CarDetails();
                int yearPurchase = rs.getInt("year_of_purchase");
                int milage = rs.getInt("milage");
                carDetails.setYear(yearPurchase);
                carDetails.setMilage(milage);

                car.setOwner(owner);
                car.setCarDetails(carDetails);
                list.add(car);
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        dbConnection.dbClose();
        return list;
    }

    @Override
    public Map<String, Integer> getCarStats() {
        Map<String,Integer> map = new LinkedHashMap<>();
        DBConnection dbConnection = DBConnection.getInstance();

        try{
            Connection conn = dbConnection.dbConnect();
            String sql = "select brand, count(id) as no_of_cars " +
                                "from car " +
                                "group by brand";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String brand = rs.getString("brand");
                int noCars = rs.getInt("no_of_cars");
                map.put(brand,noCars);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        dbConnection.dbClose();
        return map;
    }
}