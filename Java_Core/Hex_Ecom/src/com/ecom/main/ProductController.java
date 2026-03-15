package com.ecom.main;

import java.sql.*;
import java.util.*;

import com.ecom.model.Product;
import com.ecom.model.Vendor;
import com.ecom.service.ProductService;
import com.ecom.utility.ProductSortUtility;
import com.ecom.dto.VendorProductDto;

public class ProductController {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService();
        ProductSortUtility sortUtilityPrice = new ProductSortUtility("price");
        ProductSortUtility sortUtilityId = new ProductSortUtility("id");

        while(true){
            System.out.println("1. All Products with vendor and Category Info");
            System.out.println("2. Sorting Features");
            System.out.println("3. Vendor - Product stats");
            System.out.println("4. Vendor Avg Stats");
            System.out.println("5. Filter options");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            if(input == 0){
                break;
            }

            switch(input){
                case 1 -> {
                    System.out.println("----List of Products-----");

                    try{
                        List<Product> list = productService.getAllProductsWithVendorAndCategoryInfo();
                        //Collections.sort(list); // default sort based on comparable, will check compareto method and based upon it will do sorting
                        list.sort(sortUtilityId);
                        list.forEach(product->{
                            System.out.println("Product ID " + product.getId());
                            System.out.println("Product Title " + product.getTitle());
                            System.out.println("Product Stock Count " + product.getNumberStock());
                            System.out.println("Product Price: " + product.getPrice());
                            System.out.println("Vendor Name: " + product.getVendor().getName());
                            System.out.println("Category Name: " + product.getCategory().getName());
                            System.out.println("   ");
                        });
                    }
                    catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.println("Sorting Options");
                    System.out.println("Press 1. to sort by ID ASC");
                    System.out.println("Press 2. to sort by price DESC");
                    int sortInput = sc.nextInt();

                    System.out.println("---List of Products---");
                    try{
                        if(sortInput==1){
                            List<Product> list = productService.getAllProductsWithVendorAndCategoryInfo();
                            list.sort(sortUtilityId);
                            list.forEach(product->{
                                System.out.println("Product ID " + product.getId());
                                System.out.println("Product Title " + product.getTitle());
                                System.out.println("Product Stock Count " + product.getNumberStock());
                                System.out.println("Product Price: " + product.getPrice());
                                System.out.println("Vendor Name: " + product.getVendor().getName());
                                System.out.println("Category Name: " + product.getCategory().getName());
                                System.out.println("   ");
                            });
                        }
                        else if(sortInput==2){
                            List<Product> list = productService.getAllProductsWithVendorAndCategoryInfo();
                            list.sort(sortUtilityPrice);
                            list.forEach(product->{
                                System.out.println("Product ID " + product.getId());
                                System.out.println("Product Title " + product.getTitle());
                                System.out.println("Product Stock Count " + product.getNumberStock());
                                System.out.println("Product Price: " + product.getPrice());
                                System.out.println("Vendor Name: " + product.getVendor().getName());
                                System.out.println("Category Name: " + product.getCategory().getName());
                                System.out.println("   ");
                            });
                        }
                    }
                    catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
                
                case 3 -> {
                    System.out.println("---List of Products---");

                    try{
                        Map<String, Integer> map = productService.getVendorProductStat();
                        System.out.println("   Vendor  " + "\t" + "Number of Products Sold");

                        for(Map.Entry<String, Integer> entry: map.entrySet()){
                            System.out.println(entry.getKey()+"\t"+entry.getValue());
                        }
                    }
                    catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.println("---List of Products---");
                    try{
                        List<VendorProductDto> list = productService.getVendorWithNumProductsAvgSellingPrice();
                        System.out.println("Vendor Name " + " Number of Products " + " Avg Selling Price ");
                        list.forEach(record->{
                            System.out.println(record.vendorName()+"\t\t"+record.numberOfProducts()+"\t\t"+record.avgSellingPrice());
                        });
                    }
                    catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }

                case 5 ->{
                   System.out.println("1. Filter products by vendor");
                   System.out.println("2. Filter products by category");
                   int choice = sc.nextInt();
                   if(choice==1){
                        try{
                            List<Product> list = productService.getAllProductsWithVendorAndCategoryFullInfo();
                            List<Vendor> listVendor = productService.getAllVendorInfo(list);
                            System.out.println("Vendor ID"+"\t\t"+"Vendor Name");
                            listVendor.forEach(vendor->{
                                System.out.println(vendor.getId()+"\t\t"+vendor.getName());
                            });
                            System.out.println("Please select vendor ID: ");
                            int vid = sc.nextInt();
                            List<Product> listProductsByVendorId = productService.getProductsByVendorId(list,vid);
                            listProductsByVendorId.forEach(product -> {
                                    System.out.println("Product ID " + product.getId());
                                    System.out.println("Product Title " + product.getTitle());
                                    System.out.println("Product Stock Count " + product.getNumberStock());
                                    System.out.println("Product Price " + product.getPrice());
                                    System.out.println("Vendor Name: " + product.getVendor().getName());
                                    System.out.println("Category Name " + product.getCategory().getName());
                                    System.out.println("   ");
                            });
                        }
                        catch(SQLException e){
                            System.out.println(e.getMessage());
                        }
                   }
                   break;
                }

                default -> System.out.println("Invalid input");
            }
        }
        sc.close();
    }
}