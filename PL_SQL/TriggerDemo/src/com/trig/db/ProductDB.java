package com.trig.db;

import com.trig.util.DBConnection;
import com.trig.model.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class ProductDB {
    private final DBConnection dbConnection = new DBConnection();
    private Connection conn;

    public List<Product> getAllProducts(){
        this.conn = dbConnection.dbConnection();
        List<Product> list = new ArrayList<>();
        try{
            String sql = "{CALL get_all_products()}";
            CallableStatement cs = conn.prepareCall(sql);
            ResultSet rst = cs.executeQuery();

            while(rst.next()){
                Product product
                        = new Product(rst.getInt("id"),
                        rst.getString("name"),
                        rst.getBigDecimal("price"),
                        rst.getInt("stock"));
                list.add(product);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void addTransaction(int productId, int qty){
        this.conn = dbConnection.dbConnection();
        try {
            String sql = "insert into transactions(product_id, quantity, sale_date) values (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            stmt.setInt(2, qty);
            stmt.setString(3, LocalDate.now().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}