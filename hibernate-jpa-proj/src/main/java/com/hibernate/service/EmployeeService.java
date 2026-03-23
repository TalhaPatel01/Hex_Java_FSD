package com.hibernate.service;

import com.hibernate.enums.JobTitle;
import com.hibernate.model.Airline;
import com.hibernate.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private FlightService flightService;

    public EmployeeService(FlightService flightService) {
        this.flightService = flightService;
    }

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insert(Employee employee, String jobTitle, int airlineId) {
        //handling job title
        JobTitle title = JobTitle.valueOf(jobTitle);
        employee.setJobTitle(title);

        //fetching airline from id
        Airline airline = flightService.getAirlineById(airlineId);
        employee.setAirline(airline);

        em.persist(employee);
    }
}