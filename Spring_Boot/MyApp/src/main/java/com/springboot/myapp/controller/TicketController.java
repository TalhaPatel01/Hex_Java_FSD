package com.springboot.myapp.controller;

import com.springboot.myapp.dto.*;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/add")
    public ResponseEntity<?> addTicket(@Valid @RequestBody TicketReqDto ticketReqDto,
                                       Principal principal){
        String username = principal.getName();
        ticketService.addTicket(ticketReqDto,username);
        return ResponseEntity.status(201).build();
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

    //get all tickets by customer
    @GetMapping("/customer/v1")
    public List<TicketDto> getTicketByCustomer(Principal principal){
        return ticketService.getTicketByCustomer(principal.getName());
    }

    @PutMapping("/update/status/{ticketId}/v1")
    public void updateStatus(@RequestParam TicketStatus ticketStatus,
                             @PathVariable long ticketId,
                             Principal principal){
        ticketService.updateStatus(ticketStatus, ticketId, principal.getName());
    }

    @PutMapping("/update/status/{ticketId}/v2")
    public void updateStatusV2(@RequestParam TicketStatus ticketStatus,
                               @PathVariable long ticketId,
                               Principal principal){

        ticketService.updateStatusWithJpql(ticketStatus, ticketId, principal.getName());
    }

    @GetMapping("/stats")
    public List<StatDto> getTicketStats(Principal principal){
        String username = principal.getName();
        return ticketService.getTicketStats(username);
    }

    @GetMapping("/stats/v2")
    public List<StatDtoV2>  getTicketStatusV2(Principal principal){
        String username = principal.getName();
        return ticketService.getTicketStatsV2(username);
    }
}