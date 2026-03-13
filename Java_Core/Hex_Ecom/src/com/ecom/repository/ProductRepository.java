package com.ecom.repository;

import java.sql.*;
import java.util.*;

import com.ecom.dao.ProductDao;
import com.ecom.dto.VendorProductDto;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.Vendor;
import com.ecom.utility.DBConnection;
import java.math.BigDecimal;

public class ProductRepository implements ProductDao{
    DBConnection dbConnection = DBConnection.getInstance();

    @Override
    public List<Product> getAllProductsWithVendorAndCategoryInfo(){
        List<Product> list = new ArrayList<>();
        try {
            Connection conn = dbConnection.dbConnect();
            String sql = "select p.id,p.title,p.number_stock,p.price,v.name as vendorName,c.name as categoryName"
                          +" from product p "+" join vendor v on v.id=p.vendor_id "+" join category c on c.id = p.category_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int numberStock = rs.getInt("number_stock");
                BigDecimal price = rs.getBigDecimal("price");
                product.setId(id);
                product.setTitle(title);
                product.setNumberStock(numberStock);
                product.setPrice(price);

                Vendor vendor = new Vendor();
                String vendorName = rs.getString("vendorName");
                vendor.setName(vendorName);

                Category category = new Category();
                String categoryName = rs.getString("categoryName");
                category.setName(categoryName);

                product.setCategory(category);
                product.setVendor(vendor);

                list.add(product);
            }
            dbConnection.dbClose();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Map<String, Integer> getVendorProductStat() {
        Map<String, Integer> map = new LinkedHashMap<>();
        Connection conn = dbConnection.dbConnect();
        try{
            String sql = "select v.name as vendor, count(p.id) as number_of_products from product p right join vendor v on p.vendor_id = v.id group by v.name order by number_of_products desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String vendorName = rs.getString("vendor");
                int numberProducts = rs.getInt("number_of_products");
                map.put(vendorName,numberProducts);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            dbConnection.dbClose();

        }
        return map;
    }

    @Override
    public List<VendorProductDto> getVendorWithNumProductsAvgSellingPrice() {
        List<VendorProductDto> list = new ArrayList<>();
        Connection conn = dbConnection.dbConnect();

        try{
            String sql = "select v.name as vendor, count(p.id) as number_of_products, avg(p.price) as avg_selling_price"
                         + " from product p right join vendor v "+ " on p.vendor_id = v.id "+" group by v.name";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                VendorProductDto vendorProductDto = new VendorProductDto(rs.getString("vendor"),
                                                    rs.getInt("number_of_products"),
                                                    rs.getDouble("avg_selling_price"));
                list.add(vendorProductDto);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        dbConnection.dbClose();
        return list;
    }

    @Override
    public List<Product> getAllProductsWithVendorAndCategoryFullInfo() {
        List<Product> list = new ArrayList<>();
        try {
            Connection conn = dbConnection.dbConnect();
            String sql = "select p.id,p.title,p.number_stock,p.price,v.id as vid, v.name as vendorName,c.id as cid, c.name as categoryName"
                          +" from product p "+" join vendor v on v.id=p.vendor_id "+" join category c on c.id = p.category_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int numberStock = rs.getInt("number_stock");
                BigDecimal price = rs.getBigDecimal("price");
                product.setId(id);
                product.setTitle(title);
                product.setNumberStock(numberStock);
                product.setPrice(price);

                Vendor vendor = new Vendor();
                int vid = rs.getInt("vid");
                String vendorName = rs.getString("vendorName");
                vendor.setName(vendorName);
                vendor.setId(vid);

                Category category = new Category();
                int cid = rs.getInt("cid");
                String categoryName = rs.getString("categoryName");
                category.setName(categoryName);
                category.setId(cid);

                product.setCategory(category);
                product.setVendor(vendor);

                list.add(product);
            }
            dbConnection.dbClose();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
}