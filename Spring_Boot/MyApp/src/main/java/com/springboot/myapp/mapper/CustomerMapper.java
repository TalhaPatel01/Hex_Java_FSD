package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.CustomerReqDto;
import com.springboot.myapp.dto.CustomerResDto;
import com.springboot.myapp.dto.CustomerSignUpDto;
import com.springboot.myapp.model.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerReqDto customerReqDto){
        Customer customer = new Customer();
        customer.setName(customerReqDto.name());
        customer.setEmail(customerReqDto.email());
        customer.setCity(customerReqDto.city());
        return customer;
    }

    public static Customer mapToEntity(CustomerSignUpDto customerSignUpDto){
        Customer customer = new Customer();
        customer.setName(customerSignUpDto.name());
        customer.setEmail(customerSignUpDto.email());
        customer.setCity(customerSignUpDto.city());
        return customer;
    }

    public static CustomerResDto mapEntityToDto(Customer customer){
        return new CustomerResDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCity()
        );
    }
}