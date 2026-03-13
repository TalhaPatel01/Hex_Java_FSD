package com.proc.main;

import com.proc.model.Customer;
import com.proc.db.CustomerDB;
import java.util.*;

public class App {
    public static void main(String[] args){
        CustomerDB db = new CustomerDB();
        db.DBConnect();

        System.out.println("--------Get all Customers----------");
        List<Customer> list = db.getAllCustomers();
        list.forEach(System.out::println);

        System.out.println("----Get customer by city---------");
        List<Customer> listCity = db.getCustomersByCity("Bharuch");
        listCity.forEach(System.out::println);

        System.out.println("---Count customers by city----");
        System.out.println(db.getTotalCustomers("Bharuch"));

        System.out.println("---Get customers from view----");
        List<Customer> viewList = db.getCustomersView();
        viewList.forEach(System.out::println);
    }
} 