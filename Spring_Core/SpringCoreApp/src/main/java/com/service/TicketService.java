package com.service;

import com.model.Ticket;
import com.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public void insert(Ticket ticket){
        ticketRepository.insert(ticket);
    }
}