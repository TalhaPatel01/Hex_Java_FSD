package com.springboot.myapp.controller;

import com.springboot.myapp.dto.CustomerReqDto;
import com.springboot.myapp.dto.CustomerResDto;
import com.springboot.myapp.dto.CustomerSignUpDto;
import com.springboot.myapp.mapper.CustomerMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:5173/")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerReqDto customerReqDto){
        customerService.addCustomer(customerReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // customer sign up api
    @PostMapping("/sign-up")
    public ResponseEntity<?> addCustomerWithCredentials(@Valid @RequestBody CustomerSignUpDto customerSignUpDto){
        customerService.customerSignUp(customerSignUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-one")
    public CustomerResDto getCustomer(Principal principal){
        String username = principal.getName();
        Customer customer = customerService.getByUsername(username);
        return CustomerMapper.mapEntityToDto(customer);
    }

    @GetMapping("/get-all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}