package com.springboot.myapp.repository;

import com.springboot.myapp.dto.TicketDto;
import com.springboot.myapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}