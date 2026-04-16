package com.springboot.myapp.service;

import com.springboot.myapp.dto.CustomerReqDto;
import com.springboot.myapp.dto.CustomerSignUpDto;
import com.springboot.myapp.enums.Role;
import com.springboot.myapp.exception.ResourceNotFoundException;
import com.springboot.myapp.mapper.CustomerMapper;
import com.springboot.myapp.mapper.UserMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.User;
import com.springboot.myapp.repository.CustomerRepository;
import com.springboot.myapp.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepository userRepository;

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

    public void customerSignUp(@Valid CustomerSignUpDto customerSignUpDto) {
        Customer customer = CustomerMapper.mapToEntity(customerSignUpDto);

        User user = UserMapper.mapToEntity(customerSignUpDto);
        user.setRole(Role.CUSTOMER);
        user.setPassword(passwordEncoder.encode(customerSignUpDto.password()));

        userService.addUser(user);
        customer.setUser(user);
        customerRepository.save(customer);
    }

    public Customer getByUsername(String username) {
        return customerRepository.getByUsername(username);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = customerRepository.findAll();
        return list;
    }
}