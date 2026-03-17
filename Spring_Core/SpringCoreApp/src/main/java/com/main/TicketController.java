package com.main;

import com.config.ProjConfig;
import com.enums.TicketPriority;
import com.enums.TicketStatus;
import com.model.Ticket;
import com.service.TicketService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;

public class TicketController {
    public static void main(String[] args){
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        TicketService ticketService = context.getBean(TicketService.class);
        Ticket ticket = context.getBean(Ticket.class);

        ticket.setSubject("Spring");
        ticket.setDetails("Tutorial");
        ticket.setTicketPriority(TicketPriority.HIGH);
        ticket.setTicketStatus(TicketStatus.IN_PROGRESS);
        ticket.setCreatedAt(Instant.now());

        ticketService.insert(ticket);
        System.out.println("Ticket Inserted in DB");
    }
}
