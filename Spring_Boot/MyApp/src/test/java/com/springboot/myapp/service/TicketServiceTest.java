package com.springboot.myapp.service;

import com.springboot.myapp.dto.TicketResDto;
import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.exception.ResourceNotFoundException;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepository ticketRepository;

    @Test
    public void getTicketByIdWhenExists(){
        //check if ticket service is not null
        Assertions.assertNotNull(ticketService);

        //preparing obj for testing
        Ticket ticket = new Ticket();
        ticket.setId(12L);
        ticket.setSubject("test");
        ticket.setTicketPriority(TicketPriority.LOW);
        ticket.setTicketStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(Instant.now());

        // Actual Mocking: if and when you encounter a call ticketRepository.findById(12L)
        // must return this above ticket object instead of going to DB
        // this is virtual record used only for testing purpose
        when(ticketRepository.findById(12L)).thenReturn(Optional.of(ticket));

        //preparing dto to check whether dto is also working properly in service class
        TicketResDto dto = new TicketResDto(
                ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketPriority(),
                ticket.getTicketStatus(),
                ticket.getCreatedAt()
        );
        TicketResDto dto1 = new TicketResDto(
                ticket.getId(),
                ticket.getSubject(),
                TicketPriority.HIGH,
                ticket.getTicketStatus(),
                ticket.getCreatedAt()
        );

        Assertions.assertEquals(dto,ticketService.getTicketById(12L));
        Assertions.assertNotEquals(dto1,ticketService.getTicketById(12L));

        //checking whether ticketRepository is called only specific no. of times
        //ideal for fast API Processing
        Mockito.verify(ticketRepository,Mockito.times(2)).findById(12L);
    }

    @Test
    public void getTicketByIdWhenNotFound(){
        //must return empty object
        when(ticketRepository.findById(12L)).thenReturn(Optional.empty());

        //making call and verifying if exception is thrown
        Exception e = Assertions.assertThrows(ResourceNotFoundException.class,()->{
            ticketService.getTicketById(12L);
        });

        //check for exception message
        Assertions.assertEquals("Invalid id given",e.getMessage());
    }
}