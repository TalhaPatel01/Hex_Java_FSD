package com.model;

import com.enums.TicketPriority;
import com.enums.TicketStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class Ticket {
    private int id;
    private String subject;
    private String details;
    private TicketStatus ticketStatus;
    private TicketPriority ticketPriority;
    private Instant createdAt;

    public Ticket(){}

    public Ticket(int id, String subject, String details, TicketStatus ticketStatus, TicketPriority ticketPriority, Instant createdAt) {
        this.id = id;
        this.subject = subject;
        this.details = details;
        this.ticketStatus = ticketStatus;
        this.ticketPriority = ticketPriority;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public TicketPriority getTicketPriority() {
        return ticketPriority;
    }

    public void setTicketPriority(TicketPriority ticketPriority) {
        this.ticketPriority = ticketPriority;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", details='" + details + '\'' +
                ", ticketStatus=" + ticketStatus +
                ", ticketPriority=" + ticketPriority +
                ", createdAt=" + createdAt +
                '}';
    }
}