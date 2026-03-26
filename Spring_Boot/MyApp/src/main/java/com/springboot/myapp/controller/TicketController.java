package com.springboot.myapp.controller;

import com.springboot.myapp.dto.TicketFilterReqDto;
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

    @PostMapping("/add/{customerId}")
    public ResponseEntity<?> addTicket(@Valid @RequestBody TicketReqDto ticketReqDto,
                                       @PathVariable long customerId){
        ticketService.addTicket(ticketReqDto,customerId); //this will give json body
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

    @PostMapping("get/filter")    //did post because GET doesnt accept empty body
    public List<TicketResDto> getTicketByFilter(@RequestBody TicketFilterReqDto ticketFilterReqDto) {
        return ticketService.getTicketByFilter(ticketFilterReqDto);
    }

    @PutMapping("/assign-executive/{ticketId}/{executiveId}")
    public ResponseEntity<?> assignExecutive(@PathVariable long ticketId,
                                             @PathVariable long executiveId){
        ticketService.assignExecutive(ticketId,executiveId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}