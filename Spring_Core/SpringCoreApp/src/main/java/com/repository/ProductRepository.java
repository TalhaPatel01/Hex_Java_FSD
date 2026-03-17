package com.repository;

import org.springframework.stereotype.Component;

@Component
public class ProductRepository {
    public String someDbMethod(){
        return "Some db method";
    }
}