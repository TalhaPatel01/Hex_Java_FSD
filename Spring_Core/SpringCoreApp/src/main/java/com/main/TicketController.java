package com.main;

import com.config.ProjConfig;
import com.enums.TicketPriority;
import com.enums.TicketStatus;
import com.exception.TicketNotFoundException;
import com.model.Ticket;
import com.service.TicketService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class TicketController {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        TicketService ticketService = context.getBean(TicketService.class);
        Ticket ticket = context.getBean(Ticket.class);

        while(true) {
            System.out.println("1. Insert");
            System.out.println("2. Delete by id");
            System.out.println("3. Show all Tickets");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            if(input==0){
                break;
            }

            switch (input){
                case 1:
                    ticket.setSubject("Spring");
                    ticket.setDetails("Tutorial");
                    ticket.setTicketPriority(TicketPriority.HIGH);
                    ticket.setTicketStatus(TicketStatus.IN_PROGRESS);
                    ticket.setCreatedAt(Instant.now());

                    ticketService.insert(ticket);
                    System.out.println("Ticket Inserted in DB");
                    break;

                case 2:
                    System.out.println("Enter ID to Delete");
                    int id = sc.nextInt();

                    try {
                        ticketService.deleteById(id);
                        System.out.println("Ticket deleted...");
                    }
                    catch(TicketNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    List<Ticket> list = ticketService.getTickets();
                    list.forEach(System.out::println);
                    break;
            }
        }
    }
}
