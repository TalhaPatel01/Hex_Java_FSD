package com.springboot.myapp.dto;

import com.springboot.myapp.model.Ticket;

import java.util.List;

public record TicketPageResDto(
        List<Ticket> list,
        long totalRecords,
        int totalPages
) {
}
