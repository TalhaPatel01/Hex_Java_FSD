package com.main;

import com.config.ProjConfig;
import com.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductController {
    public static void main(String[] args) {
        //ProductService productService; //wrong
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        ProductService productService = context.getBean(ProductService.class);
        System.out.println(productService.repoMethod());
    }
}