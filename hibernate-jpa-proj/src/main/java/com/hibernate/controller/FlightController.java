package com.hibernate.controller;

import com.hibernate.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class FlightController {
    public static void main(String[] args){
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        LocalContainerEntityManagerFactoryBean emf = context.getBean(LocalContainerEntityManagerFactoryBean.class);
        System.out.println(emf);
        context.close();
    }
}