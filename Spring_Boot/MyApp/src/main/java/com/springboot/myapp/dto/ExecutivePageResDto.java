package com.springboot.myapp.dto;

import com.springboot.myapp.model.Executive;

import java.util.List;

public record ExecutivePageResDto(
        List<Executive> list,
        long totalRecords,
        int totalPages
) {
}
