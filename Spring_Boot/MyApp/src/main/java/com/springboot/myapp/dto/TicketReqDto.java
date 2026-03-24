package com.springboot.myapp.dto;

import com.springboot.myapp.enums.TicketPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketReqDto(
        @NotNull
        @NotBlank(message = "subject cannot be blank/null")
        @Size(min = 3,max = 255)
        String subject,

        @NotNull
        @NotBlank(message = "details cannot be blank/null")
        @Size(min = 3,max = 1000)
        String details,

        TicketPriority priority
) {
}