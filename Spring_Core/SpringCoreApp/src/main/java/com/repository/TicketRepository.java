package com.repository;

import com.config.ProjConfig;
import com.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TicketRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Ticket ticket){
        String sql = "insert into ticket(subject, details, t_priority,t_status,createdAt) " +
                "  values (?,?,?,?,?)";
        jdbcTemplate.update(sql,ticket.getSubject(),
                ticket.getDetails(),
                ticket.getTicketPriority().toString(),
                ticket.getTicketStatus().toString(),
                ticket.getCreatedAt());
    }
}
