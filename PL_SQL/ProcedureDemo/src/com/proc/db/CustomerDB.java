package com.proc.db;

import java.sql.*;
import java.util.*;
import com.proc.model.Customer;

public class CustomerDB {
    private Connection conn;

    public void DBConnect(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hex_java_fsd", "root", "talhapatel123");
            System.out.println("Connection successfull");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void DBClose(){
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Customer> getAllCustomers(){
        List<Customer> list = new ArrayList<>();

        try{
            CallableStatement cs = conn.prepareCall("{CALL get_all_customers()}");
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                list.add(new Customer(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("city")));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Customer> getCustomersByCity(String city){
        List<Customer> list = new ArrayList<>();

        try{
            CallableStatement cs = conn.prepareCall("{CALL get_customers_by_city(?)}");
            cs.setString(1,city);
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                list.add(new Customer(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("city")));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int getTotalCustomers(String city){
        int total=0;

        try {
            CallableStatement cs = conn.prepareCall("{CALL count_customers(?,?)}");
            cs.setString(1,city);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeQuery();
            total=cs.getInt(2);
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

    public List<Customer> getCustomersView(){
        List<Customer> list = new ArrayList<>();
        
        try {
            String sql = "select * from customer_view";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rst =  ps.executeQuery();

            while(rst.next()){
                Customer customer
                        = new Customer(rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("city"));
                list.add(customer);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}