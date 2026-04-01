package com.springboot.myapp.repository;

import com.springboot.myapp.dto.TicketDto;
import com.springboot.myapp.dto.TicketFilterReqDto;
import com.springboot.myapp.dto.TicketResDto;
import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("""
            select t from Ticket t
            where (?1 IS NULL OR t.ticketPriority = ?1) AND (?2 IS NULL OR t.ticketStatus = ?2)
            """)
    List<Ticket> getTicketByPriorityAndStatus(TicketPriority priority, TicketStatus status);

    @Query("""
            select t
            from Ticket t
            where t.customer.user.username=?1
            """)
    List<Ticket> getTicketByCustomer(String username);
}

/*
 JpaRepository
    save(T) : T
    findAll() : List<T>
    findById(id) : T
    deleteById(id)
    saveAll(list)
* */