package com.hibernate.service;

import com.hibernate.model.Airline;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @PersistenceContext //activates EntityManager
    private EntityManager em;

    @Transactional
    public void insert(Airline airline) {
        em.persist(airline);
    }
}