package com.ecom.dao;

import com.ecom.dto.VendorProductDto;
import com.ecom.model.Product;

import java.util.*;

public interface ProductDao {
    List<Product>  getAllProductsWithVendorAndCategoryInfo();
    Map<String, Integer> getVendorProductStat();
    List<VendorProductDto> getVendorWithNumProductsAvgSellingPrice();
    List<Product>  getAllProductsWithVendorAndCategoryFullInfo();
}