package com.springboot.myapp.service;

import com.springboot.myapp.dto.TicketPageResDto;
import com.springboot.myapp.dto.TicketReqDto;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.mapper.TicketMapper;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.repository.TicketRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public void addTicket(@Valid TicketReqDto ticketReqDto) {
        //1. map dto to entity using mapper
        Ticket ticket = TicketMapper.mapToEntity(ticketReqDto);

        //2. add additional details
        ticket.setTicketStatus(TicketStatus.OPEN);

        //3. add to db
        ticketRepository.save(ticket);
    }

    public TicketPageResDto getAllTickets(int page, int size) {
        // object of pageable and pass page and size
        Pageable pageable = PageRequest.of(page,size);
        //starting id of page = page*size+1

        Page<Ticket> pageTicket = ticketRepository.findAll(pageable);
        long totalRecords = pageTicket.getTotalElements();
        int totalPages = pageTicket.getTotalPages();

        return new TicketPageResDto(
                pageTicket.toList(),
                totalRecords,
                totalPages
        );
    }
}