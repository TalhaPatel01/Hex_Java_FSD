package com.hibernate.service;

import com.hibernate.dto.FlightDto;
import com.hibernate.enums.JobTitle;
import com.hibernate.model.Airline;
import com.hibernate.model.Employee;
import com.hibernate.model.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
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

    public List<Employee> fetchEmployeesByJobTitle(String jobTitle) {
        //0. validate job title
        JobTitle.valueOf(jobTitle);

        //1. Build criteria query using builder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //2. define result type by creating object: select (ctq)
        CriteriaQuery ctq = cb.createQuery(Employee.class);

        //3. from
        Root<Employee> employee = ctq.from(Employee.class);

        //4. where
        Predicate predicate = cb.equal(employee.get("jobTitle"),jobTitle);
        ctq.where(predicate);

        //5. execute
        return em.createQuery(ctq).getResultList();
    }

    @Transactional
    public void deleteEmployeeById(int employeeId) {
        String jpql = "delete from Employee e where e.id=:employeeId";
        em.createQuery(jpql).setParameter("employeeId",employeeId).executeUpdate();
    }

    @Transactional
    public void updateEmployee(Employee updateEmployee, int id) {
        //1. Criteria Builder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //2. Criteria Update
        CriteriaUpdate criteriaUpdate = cb.createCriteriaUpdate(Employee.class);

        //3. from
        Root<Employee> employee = criteriaUpdate.from(Employee.class);

        //4. set
        if(updateEmployee.getName()!=null && !updateEmployee.getName().isEmpty()){
            criteriaUpdate.set(employee.get("name"),updateEmployee.getName());
        }
        if(updateEmployee.getEmail()!=null && !updateEmployee.getEmail().isEmpty()){
            criteriaUpdate.set(employee.get("email"),updateEmployee.getEmail());
        }
        if(updateEmployee.getJobTitle()!=null){
            criteriaUpdate.set(employee.get("jobTitle"),updateEmployee.getJobTitle());
        }

        //5. where
        Predicate predicate = cb.equal(employee.get("id"),id);
        criteriaUpdate.where(predicate);

        //6. execute
        em.createQuery(criteriaUpdate).executeUpdate();
    }
}