package com.springboot.myapp.repository;

import com.springboot.myapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}

/*
 JpaRepository
    save(T) : T
    findAll() : List<T>
    findById(id) : T
    deleteById(id)
    saveAll(list)
* */