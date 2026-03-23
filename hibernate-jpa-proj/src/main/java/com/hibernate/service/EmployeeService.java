package com.hibernate.service;

import com.hibernate.dto.FlightDto;
import com.hibernate.enums.JobTitle;
import com.hibernate.model.Airline;
import com.hibernate.model.Employee;
import com.hibernate.model.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final FlightService flightService;

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

    @Transactional
    public List<?> showAllEmployees() {
        String jpql = "select a from Employee a";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    public FlightDto fetchEmployeeAndAirlineByFlight(int flightId) {
        // fetch flight using id
        Flight flight = flightService.getFlightById(flightId);

        //query to get employees
        String jpql = "select e from Employee e where e.airline.id =: flightId";
        Query query = em.createQuery(jpql);
        query.setParameter("flightId",flightId);
        List<Employee> employeeList = query.getResultList();

        return new FlightDto(
                flight.getFlightNumber(),
                flight.getSource(),
                flight.getDestination(),
                flight.getAirline().getName(),
                employeeList
        );
    }
}