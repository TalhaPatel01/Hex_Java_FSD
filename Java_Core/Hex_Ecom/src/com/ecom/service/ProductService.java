package com.ecom.service;

import java.sql.*;
import java.util.*;

import com.ecom.repository.ProductRepository;
import com.ecom.dto.VendorProductDto;
import com.ecom.model.*;

public class ProductService {
    ProductRepository productRepository = new ProductRepository();

    public List<Product> getAllProductsWithVendorAndCategoryInfo() throws SQLException{
        return productRepository.getAllProductsWithVendorAndCategoryInfo();
    }

    public Map<String, Integer> getVendorProductStat() throws SQLException{
        return productRepository.getVendorProductStat();
    }

    public List<VendorProductDto> getVendorWithNumProductsAvgSellingPrice() throws SQLException{
        return productRepository.getVendorWithNumProductsAvgSellingPrice();
    }

    public List<Product> getAllProductsWithVendorAndCategoryFullInfo() throws SQLException{
        List<Product> list = productRepository.getAllProductsWithVendorAndCategoryFullInfo();
        return list;
    }

    public List<Vendor> getAllVendorInfo(List<Product> list) {
        return list.stream()
                .map(Product :: getVendor)
                .distinct() //unique vendors --> v1 v2
                .sorted(Comparator.comparing(Vendor::getId))
                .toList(); //List<Vendor>
    }

    public List<Product> getProductsByVendorId(List<Product> list, int vid) { //1=1   2=1   1=1  3=1
        return list.stream() //Stream<Product>
                .filter(product -> product.getVendor().getId() == vid) //Stream<Product> --> filtered list
                .toList(); //List<Product>
    }
}