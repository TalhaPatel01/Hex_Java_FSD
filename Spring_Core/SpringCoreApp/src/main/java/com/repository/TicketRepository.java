package com.repository;

import com.enums.TicketPriority;
import com.enums.TicketStatus;
import com.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public int deleteById(int id) {
        String sql = "delete from ticket where id=?";
        return jdbcTemplate.update(sql,id);
    }

    public List<Ticket> fetchAllTickets() {
        String sql = "select * from ticket";
        return jdbcTemplate.query(sql, new RowMapper<Ticket>() {
            @Override
            public Ticket mapRow(@NonNull ResultSet rst, int rowNum) throws SQLException {
                return new Ticket(
                        rst.getInt("id"),
                        rst.getString("subject"),
                        rst.getString("details"),
                        TicketStatus.valueOf(rst.getString("t_status")),
                        TicketPriority.valueOf(rst.getString("t_priority")),
                        rst.getTimestamp("createdAt").toInstant()
                );
            }
        });
    }
}