package com.hibernate.service;

import com.hibernate.exception.ResourceNotFoundException;
import com.hibernate.model.Airline;
import com.hibernate.model.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @PersistenceContext //activates EntityManager
    private EntityManager em;

    @Transactional
    public void insert(Airline airline) {
        em.persist(airline);
    }

    public List<?> getAllAirlines() {
        String sql = "select a from Airline a";
        Query query = em.createQuery(sql, Airline.class);
        return query.getResultList();
        //?: wildcard to avoid downcast
    }

    public Airline getAirlineById(int airlineId) {
        Airline airline = em.find(Airline.class,airlineId);
        if(airline==null){
            throw new ResourceNotFoundException("Airline Not Found");
        }
        return airline;
    }

    @Transactional
    public void insertFlight(Flight flight) {
        em.persist(flight);
    }

    @Transactional
    public List<?> getAllFlights() {
        String jhql = "select a from Flight a";
        Query query = em.createQuery(jhql);
        return query.getResultList();
    }
}

/*
* SQL: select * from airlines -- native SQL: on DB
* HQL/JPQL: it ops on class instead of DB
*           select a from Airline a
* */