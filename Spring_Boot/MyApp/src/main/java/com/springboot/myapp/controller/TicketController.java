package com.springboot.myapp.controller;

import com.springboot.myapp.dto.TicketReqDto;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/api/ticket/add")
    public ResponseEntity<?> addTicket(@Valid @RequestBody TicketReqDto ticketReqDto){
        ticketService.addTicket(ticketReqDto); //this will give json body
        return ResponseEntity.status(201).build(); //this will hide and will not give anything
    }
}