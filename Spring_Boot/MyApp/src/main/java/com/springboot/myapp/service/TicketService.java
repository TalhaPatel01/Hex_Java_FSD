package com.springboot.myapp.service;

import com.springboot.myapp.dto.*;
import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.exception.ResourceNotFoundException;
import com.springboot.myapp.mapper.TicketMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.Executive;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.repository.CustomerRepository;
import com.springboot.myapp.repository.ExecutiveRepository;
import com.springboot.myapp.repository.TicketRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;
    private final CustomerService customerService;

    public void addTicket(@Valid TicketReqDto ticketReqDto,String username) {
        //0. get customer by id
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(()->new ResourceNotFoundException("Customer with this id not found"));

        Customer customer = customerService.getByUsername(username);

        //1. map dto to entity using mapper
        Ticket ticket = TicketMapper.mapToEntity(ticketReqDto);

        //2. add additional details + customer
        ticket.setTicketStatus(TicketStatus.OPEN);
        ticket.setCustomer(customer);

        //3. add to db
        ticketRepository.save(ticket);
    }

    public TicketPageResDto getAllTickets(int page, int size) {
        // object of pageable and pass page and size
        Pageable pageable = PageRequest.of(page,size);
        //starting id of page = page*size+1

        Page<Ticket> pageTicket = ticketRepository.findAll(pageable);
        long totalRecords = pageTicket.getTotalElements();
        int totalPages = pageTicket.getTotalPages();

        //converts List<Ticket> to List<TicketResDto>
        List<TicketResDto> list = pageTicket
                .stream()
                .map(TicketMapper::mapToDto)
                .toList();

        return new TicketPageResDto(
                list,
                totalRecords,
                totalPages
        );
    }

    public TicketResDto getTicketById(long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Invalid id given"));

        return new TicketResDto(
              ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketPriority(),
                ticket.getTicketStatus(),
                ticket.getCreatedAt()
        );
    }

    public List<TicketResDto> getTicketByFilter(TicketFilterReqDto dto) {
        if(dto.priority()==null && dto.status()==null){
            return List.of();
        }

        TicketPriority priority = (dto.priority() != null && !dto.priority().isEmpty())
                ? TicketPriority.valueOf(dto.priority()) : null;

        TicketStatus status = (dto.status() != null && !dto.status().isEmpty())
                ? TicketStatus.valueOf(dto.status()) : null;

        List<Ticket> tickets = ticketRepository.getTicketByPriorityAndStatus(priority,status);

        return tickets
                .stream()
                .map(TicketMapper::mapToDto)
                .toList();
    }

    public void assignExecutive(long ticketId, long executiveId) {
        //1. fetch ticket
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(()->new ResourceNotFoundException("Ticket with this id not found"));

        //2.fetch executive
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(()->new ResourceNotFoundException("Executive with this id not found"));

        //3. assign executive to ticket
        ticket.setExecutive(executive);

        //4. save ticket again
        ticketRepository.save(ticket);
    }

    public List<TicketDto> getTicketByCustomer(String username) {
        List<Ticket> list = ticketRepository.getTicketByCustomer(username);

        return list
                .stream()
                .map(TicketMapper::maptoTicketDto)
                .toList();
    }
}