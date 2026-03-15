package com.car.utility;

import java.sql.*;

public class DBConnection {
    private Connection conn;
    private static final DBConnection dbConnection;

    static{
        dbConnection=new DBConnection();
    }
    private DBConnection(){}

    public static DBConnection getInstance(){
        return dbConnection;
    }

    public Connection dbConnect(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hex_car_proj?useSSL=false", "root", "talhapatel123");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void dbClose(){
        try{
            conn.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
