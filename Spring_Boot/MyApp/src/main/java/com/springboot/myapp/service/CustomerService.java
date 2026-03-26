package com.springboot.myapp.service;

import com.springboot.myapp.dto.CustomerReqDto;
import com.springboot.myapp.exception.ResourceNotFoundException;
import com.springboot.myapp.mapper.CustomerMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void addCustomer(@Valid CustomerReqDto customerReqDto) {
        //1. map dto to mapper
        Customer customer = CustomerMapper.mapToCustomer(customerReqDto);

        //2. add to DB
        customerRepository.save(customer);
    }

    public Customer getById(long customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer id not found"));
    }
}
