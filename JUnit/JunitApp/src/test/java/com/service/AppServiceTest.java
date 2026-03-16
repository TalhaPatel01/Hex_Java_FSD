package com.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppServiceTest {
    AppService appService = new AppService();

    @Test
    public void addTest(){
        Assertions.assertEquals(1,appService.add(0,1));
        Assertions.assertEquals(2,appService.add(1,1));
        Assertions.assertNotEquals(3,appService.add(2,0));
    }
}