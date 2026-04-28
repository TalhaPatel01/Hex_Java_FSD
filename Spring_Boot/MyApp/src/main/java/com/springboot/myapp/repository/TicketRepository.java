package com.springboot.myapp.repository;

import com.springboot.myapp.dto.StatDtoV2;
import com.springboot.myapp.dto.TicketDto;
import com.springboot.myapp.dto.TicketFilterReqDto;
import com.springboot.myapp.dto.TicketResDto;
import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

    @Modifying
    @Transactional
    @Query("""
            update Ticket t
            SET t.ticketStatus = ?1
            where t.id = ?2
            """)
    void updateStatusWithJpql(TicketStatus ticketStatus, long ticketId);

    @Query("""
            select t from Ticket t
            where t.customer.user.username = ?1
            """)
    List<Ticket> getTicketByCustomerUsername(String username);

    @Query("""
            select new com.springboot.myapp.dto.StatDtoV2(t.ticketStatus, count(t.id))
            from Ticket t
            where t.customer.user.username = ?1
            group by t.ticketStatus
            """)
    List<StatDtoV2> getTicketByCustomerUsernameV2(String username);

    @Query("""
            select t from Ticket t
            where t.customer.id = ?1
            """)
    List<Ticket> getTicketByCustomerId(long customerId);
}

/*
 JpaRepository
    save(T) : T
    findAll() : List<T>
    findById(id) : T
    deleteById(id)
    saveAll(list)
* */