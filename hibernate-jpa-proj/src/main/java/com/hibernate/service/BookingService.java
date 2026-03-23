package com.hibernate.service;

import com.hibernate.model.Booking;
import com.hibernate.model.Employee;
import com.hibernate.model.Flight;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class BookingService {
    private FlightService flightService;

    public BookingService(FlightService flightService) {
        this.flightService = flightService;
    }

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insert(Booking booking, int flightId) {
        //setting flight
        Flight flight = flightService.getFlightById(flightId);
        booking.setFlight(flight);
        em.persist(booking);
    }

    @Transactional
    public List<Booking> fetchBookingByDate(String date){
        LocalDate localDate = LocalDate.parse(date);
        Instant start = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = localDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        String jpql = "select b from Booking b where b.dateOfBooking between :start and :end";
        Query query = em.createQuery(jpql);
        query.setParameter("start",start);
        query.setParameter("end",end);
        return query.getResultList();
    }

    @Transactional
    public void updateBooking(Booking booking, int id) {
        //1. criteria builder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //2. criteria update, select
        CriteriaUpdate criteriaUpdate = cb.createCriteriaUpdate(Booking.class);

        //3. from
        Root<Booking> root = criteriaUpdate.from(Booking.class);

        //4. set
        if(booking.getPassengerName()!=null && !booking.getPassengerName().isEmpty()){
            criteriaUpdate.set("passengerName",booking.getPassengerName());
        }
        if(booking.getSeatNumber()!=null && !booking.getSeatNumber().isEmpty()){
            criteriaUpdate.set("seatNumber",booking.getSeatNumber());
        }

        //5. where
        Predicate predicate = cb.equal(root.get("id"),id);
        criteriaUpdate.where(predicate);

        //6. execution
        em.createQuery(criteriaUpdate).executeUpdate();
    }

    @Transactional
    public List<Object[]> getSumOnDate(String sumDate) {
        LocalDate localDate = LocalDate.parse(sumDate);
        Instant start = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = localDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        String jpql = "select b.flight.id, sum(b.fare) from Booking b "+
                " where dateOfBooking between :start and :end "+
                " group by b.flight.id";

        Query query = em.createQuery(jpql);
        query.setParameter("start",start);
        query.setParameter("end",end);

        return query.getResultList();
    }
}