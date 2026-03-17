package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.*")
public class ProjConfig { //spring detects configuration files automatically
    static {
        System.out.println("Project config detected");
    }
}
