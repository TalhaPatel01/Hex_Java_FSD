package com.service;

import com.exception.TicketNotFoundException;
import com.model.Ticket;
import com.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public void insert(Ticket ticket){
        ticketRepository.insert(ticket);
    }

    public void deleteById(int id){
        int updatedRows = ticketRepository.deleteById(id);
        if(updatedRows==0){
            throw new TicketNotFoundException("Invalid ID..");
        }
    }

    public List<Ticket> getTickets(){
        return ticketRepository.fetchAllTickets();
    }
}