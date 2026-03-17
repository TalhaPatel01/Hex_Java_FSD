package com.service;

import com.repository.ProductRepository;
import com.utility.CartUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartUtility cartUtility;

    public String repoMethod(){
        System.out.println(cartUtility.utilMethod());
        return productRepository.someDbMethod();
    }
}
