package com.springboot.myapp.dto;

public record CustomerResDto(
        long id,
        String name,
        String email,
        String city
) {
}