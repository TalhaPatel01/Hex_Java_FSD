package com.springboot.myapp.dto;

public record TicketFilterReqDto(
        String priority,
        String status
) {
}
