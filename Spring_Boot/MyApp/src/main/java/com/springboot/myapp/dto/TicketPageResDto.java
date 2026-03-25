package com.springboot.myapp.dto;

import java.util.List;

public record TicketPageResDto(
        List<TicketResDto> list,
        long totalRecords,
        int totalPages
) {
}
