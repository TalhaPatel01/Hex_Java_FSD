package com.springboot.myapp.controller;

import com.springboot.myapp.dto.TicketPageResDto;
import com.springboot.myapp.dto.TicketReqDto;
import com.springboot.myapp.dto.TicketResDto;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<?> addTicket(@Valid @RequestBody TicketReqDto ticketReqDto){
        ticketService.addTicket(ticketReqDto); //this will give json body
        return ResponseEntity.status(201).build(); //this will hide and will not give anything
    }

    @GetMapping("/get-all")
    public TicketPageResDto getAllTickets(@RequestParam (value = "page", required = false, defaultValue = "0") int page,
                                          @RequestParam (value = "size", required = false, defaultValue = "0") int size){
        return ticketService.getAllTickets(page,size);
    }

    @GetMapping("/get/{id}")
    public TicketResDto getTicketById(@PathVariable long id){
        return ticketService.getTicketById(id);
    }
}