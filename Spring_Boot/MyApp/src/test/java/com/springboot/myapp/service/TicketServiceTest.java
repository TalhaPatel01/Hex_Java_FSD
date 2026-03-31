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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
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

    @Test
    public void getAllTicketsTest(){
        /* Prepare the List. */
        Ticket ticket1 = new Ticket();
        ticket1.setId(12L);
        ticket1.setSubject("test subject");
        ticket1.setTicketPriority(TicketPriority.LOW);
        ticket1.setTicketStatus(TicketStatus.OPEN);
        ticket1.setCreatedAt(Instant.now());
        Ticket ticket2 = new Ticket();
        ticket2.setId(14L);
        ticket2.setSubject("test subject");
        ticket2.setTicketPriority(TicketPriority.HIGH);
        ticket2.setTicketStatus(TicketStatus.CLOSED);
        ticket2.setCreatedAt(Instant.now());
        List<Ticket> list = List.of(ticket1,ticket2);

        //create page obj
        Page<Ticket> pageTicket = new PageImpl<>(list);
        int page=0;
        int size=2;

        Pageable pageable = PageRequest.of(page,size);

        //mocking repository call
        when(ticketRepository.findAll(pageable)).thenReturn(pageTicket);
        Assertions.assertEquals(2,ticketService.getAllTickets(0,2).list().size());

        Page<Ticket> pageTicket1 = new PageImpl<>(list.subList(0,1));
        page = 0;
        size = 1;

        Pageable pageable1 = PageRequest.of(page,size);
        // Mock the repository call for findALL()
        when(ticketRepository.findAll(pageable1)).thenReturn(pageTicket1);
        Assertions.assertEquals(1,ticketService.getAllTickets(0,1).list().size());
    }
}