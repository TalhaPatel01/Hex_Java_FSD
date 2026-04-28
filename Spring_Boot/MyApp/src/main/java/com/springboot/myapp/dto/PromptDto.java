package com.springboot.myapp.dto;

import jakarta.validation.constraints.NotBlank;

public record PromptDto(
        @NotBlank(message = "Please ask something to gt an answer")
        String prompt
) {
}