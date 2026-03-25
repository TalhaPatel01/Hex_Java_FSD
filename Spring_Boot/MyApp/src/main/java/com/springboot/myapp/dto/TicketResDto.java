package com.springboot.myapp.dto;

import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;

import java.time.Instant;

public record TicketResDto(
        Long id,
        String subject,
        TicketPriority priority,
        TicketStatus status,
        Instant createdAt
) {
}