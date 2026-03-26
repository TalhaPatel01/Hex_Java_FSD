package com.springboot.myapp.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PlanUtility {

    public LocalDate computeEndDate(LocalDate localDate, int days) {
        return localDate.plusDays(days);
    }
}