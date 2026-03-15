package com.trig.util;

import java.sql.*;

public class DBConnection {
    private Connection conn;

    public Connection dbConnection(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hex_java_fsd", "root", "talhapatel123");
        } catch (SQLException e) {
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