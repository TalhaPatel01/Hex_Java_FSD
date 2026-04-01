package com.springboot.myapp.controller;

import com.springboot.myapp.dto.CustomerPlanReqDto;
import com.springboot.myapp.dto.CustomerPlanResDto;
import com.springboot.myapp.service.CustomerPlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/customer/plan")
@AllArgsConstructor
public class CustomerPlanController {
    private final CustomerPlanService customerPlanService;

    // admin adds plan to customer
    @PostMapping("/add/admin/{customerId}/{planId}/v2")
    public ResponseEntity<?> addCustomerPlanByAdmin(@RequestBody CustomerPlanReqDto customerPlanReqDto,
                                             @PathVariable long customerId,
                                             @PathVariable long planId){
        customerPlanService.addCustomerPlanByAdmin(customerPlanReqDto,customerId,planId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //customer buys plan
    @PostMapping("/add/{planId}/v1")
    public ResponseEntity<?> buyPlan(@RequestBody CustomerPlanReqDto customerPlanReqDto,
                                     Principal principal, @PathVariable long planId){
        String username = principal.getName();
        customerPlanService.buyPlan(customerPlanReqDto,username,planId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-customers/{planId}")
    public List<CustomerPlanResDto> getCustomersByPlanId(@PathVariable long planId){
        return customerPlanService.getCustomersByPlanId(planId);
    }
}