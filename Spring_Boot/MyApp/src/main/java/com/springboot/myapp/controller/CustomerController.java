package com.springboot.myapp.controller;

import com.springboot.myapp.dto.CustomerReqDto;
import com.springboot.myapp.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/api/customer/add")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerReqDto customerReqDto){
        customerService.addCustomer(customerReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
