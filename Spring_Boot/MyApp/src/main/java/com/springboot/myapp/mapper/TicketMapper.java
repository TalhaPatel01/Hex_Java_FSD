package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.TicketReqDto;
import com.springboot.myapp.model.Ticket;

public class TicketMapper {
    public static Ticket mapToEntity(TicketReqDto ticketReqDto){
        Ticket ticket = new Ticket();
        ticket.setSubject(ticketReqDto.subject());
        ticket.setDetails(ticketReqDto.details());
        ticket.setTicketPriority(ticketReqDto.priority());
        return ticket;
    }
}
