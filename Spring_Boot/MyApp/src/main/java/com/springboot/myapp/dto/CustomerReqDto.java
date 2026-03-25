package com.springboot.myapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerReqDto(
        @NotNull
        @NotBlank(message = "name cannot be null/blank")
        String name,

        @NotNull
        @NotBlank(message = "email cannot be null/blank")
        String email,

        @NotNull
        @NotBlank(message = "city cannot be null/blank")
        String city
) {
}