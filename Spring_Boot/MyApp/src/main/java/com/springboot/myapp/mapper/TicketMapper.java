package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.TicketReqDto;
import com.springboot.myapp.dto.TicketResDto;
import com.springboot.myapp.model.Ticket;

public class TicketMapper {
    public static Ticket mapToEntity(TicketReqDto ticketReqDto){
        Ticket ticket = new Ticket();
        ticket.setSubject(ticketReqDto.subject());
        ticket.setDetails(ticketReqDto.details());
        ticket.setTicketPriority(ticketReqDto.priority());
        return ticket;
    }

    public static TicketResDto mapToDto(Ticket ticket){
        return new TicketResDto(
              ticket.getId(),
              ticket.getSubject(),
              ticket.getTicketPriority(),
              ticket.getTicketStatus(),
              ticket.getCreatedAt()
        );
    }
}
