package com.springboot.myapp.controller;

import com.springboot.myapp.dto.CustomerReqDto;
import com.springboot.myapp.dto.CustomerSignUpDto;
import com.springboot.myapp.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerReqDto customerReqDto){
        customerService.addCustomer(customerReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // customer sign up api
    @PostMapping("/sign-up")
    public void addCustomerWithCredentials(@Valid @RequestBody CustomerSignUpDto customerSignUpDto){
        customerService.customerSignUp(customerSignUpDto);
    }
}