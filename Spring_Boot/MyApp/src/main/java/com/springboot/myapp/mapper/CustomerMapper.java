package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.CustomerReqDto;
import com.springboot.myapp.model.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerReqDto customerReqDto){
        Customer customer = new Customer();
        customer.setName(customerReqDto.name());
        customer.setEmail(customerReqDto.email());
        customer.setCity(customerReqDto.city());
        return customer;
    }
}
