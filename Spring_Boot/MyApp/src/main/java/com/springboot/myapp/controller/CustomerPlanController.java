package com.springboot.myapp.controller;

import com.springboot.myapp.dto.CustomerPlanReqDto;
import com.springboot.myapp.service.CustomerPlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/plan")
@AllArgsConstructor
public class CustomerPlanController {
    private final CustomerPlanService customerPlanService;

    @PostMapping("/add/{customerId}/{planId}")
    public ResponseEntity<?> addCustomerPlan(@RequestBody CustomerPlanReqDto customerPlanReqDto,
                                             @PathVariable long customerId,
                                             @PathVariable long planId){
        customerPlanService.addCustomerPlan(customerPlanReqDto,customerId,planId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}