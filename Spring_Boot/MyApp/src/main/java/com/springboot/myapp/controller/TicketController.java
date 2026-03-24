package com.springboot.myapp.controller;

import com.springboot.myapp.dto.TicketReqDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @PostMapping("/api/ticket/add")
    public TicketReqDto addTicket(@Valid @RequestBody TicketReqDto ticketReqDto){
        return ticketReqDto;
    }
}