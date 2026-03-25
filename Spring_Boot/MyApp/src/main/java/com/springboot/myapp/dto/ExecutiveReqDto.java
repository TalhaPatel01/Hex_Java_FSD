package com.springboot.myapp.dto;

import com.springboot.myapp.enums.JobTitle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExecutiveReqDto(
        @NotNull
        @NotBlank(message = "name cannot be null/blank")
        String name,

        JobTitle jobTitle
) {
}
