package com.ecom.utility;

import java.util.Comparator;

import com.ecom.model.Product;

public class ProductSortUtility implements Comparator<Product>{
    private String field;

    public ProductSortUtility(String field){
        this.field=field;
    }

    public ProductSortUtility(){}

    @Override
    public int compare(Product p1, Product p2) {
        if(field.equals("price")){
            return p1.getPrice().compareTo(p2.getPrice());
        }
        if(field.equals("id")){
            return p1.getId()-p2.getId();
        }
        return 0;
    }
}