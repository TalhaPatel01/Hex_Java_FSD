package com.springboot.myapp.service;

import com.springboot.myapp.dto.*;
import com.springboot.myapp.enums.Role;
import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.exception.ResourceNotFoundException;
import com.springboot.myapp.exception.TicketUpdatePermissionException;
import com.springboot.myapp.mapper.TicketMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.Executive;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.model.User;
import com.springboot.myapp.repository.CustomerRepository;
import com.springboot.myapp.repository.ExecutiveRepository;
import com.springboot.myapp.repository.TicketRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;
    private final CustomerService customerService;
    private final UserService userService;

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
        log.atLevel(Level.INFO).log("Called: assignExecutiveToTicket - assigning ticket to executive by ids");
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

        log.atLevel(Level.INFO).log("Assigned Executive to Ticket Completed: assignExecutiveToTicket");
    }

    public List<TicketDto> getTicketByCustomer(String username) {
        List<Ticket> list = ticketRepository.getTicketByCustomer(username);

        return list
                .stream()
                .map(TicketMapper::maptoTicketDto)
                .toList();
    }

    public void updateStatus(TicketStatus ticketStatus, long ticketId, String loggedInUsername) {
        Ticket ticket  = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket Id Invalid."));

        // This user is trying to update ticket
        User user = (User) userService.loadUserByUsername(loggedInUsername);

        // Check if the ticket belongs to this user
        //If id of loggedIn user is equal to the id of ticket that needs to be updated. then let it go thru
        //else throw an Exception

        if(user.getRole().equals(Role.CUSTOMER)){
            if( ticket.getCustomer().getUser().getId() != user.getId())
                throw new TicketUpdatePermissionException("Customer does not own this ticket");

        }
        if(user.getRole().equals(Role.EXECUTIVE)){
            if(ticket.getExecutive() == null)
                throw new TicketUpdatePermissionException("Executive does not own this ticket");

            if( ticket.getExecutive().getUser().getId() != user.getId())
                throw new TicketUpdatePermissionException("Executive does not manage this ticket");

        }
        ticket.setTicketStatus(ticketStatus);
        ticketRepository.save(ticket);
    }

    public void updateStatusWithJpql(TicketStatus ticketStatus, long ticketId, String loggedInUsername) {
        Ticket ticket  = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket Id Invalid."));


        // This user is trying to update ticket
        User user = (User) userService.loadUserByUsername(loggedInUsername);

        // Check if the icket belongs to this user
        //If id of loggedIn user is equal to the id of ticket that needs to be updated. then let it go thru
        //else throw an Exception

        if(user.getRole().equals(Role.CUSTOMER)){
            if( ticket.getCustomer().getUser().getId() != user.getId())
                throw new TicketUpdatePermissionException("Customer does not own this ticket");

        }
        if(user.getRole().equals(Role.EXECUTIVE)){
            if(ticket.getExecutive() == null)
                throw new TicketUpdatePermissionException("Executive does not own this ticket");

            if( ticket.getExecutive().getUser().getId() != user.getId())
                throw new TicketUpdatePermissionException("Executive does not manage this ticket");

        }
        ticketRepository.updateStatusWithJpql(ticketStatus,ticketId);
    }

    public List<StatDto> getTicketStats(String username) {
        List<Ticket> list = ticketRepository.getTicketByCustomerUsername(username);

        List<Ticket> openTickets = list
                .stream()
                .filter(t->t.getTicketStatus().equals(TicketStatus.OPEN))
                .toList();

        List<Ticket> closedTickets = list
                .stream()
                .filter(t->t.getTicketStatus().equals(TicketStatus.CLOSED))
                .toList();

        List<Ticket> inProcessTickets = list
                .stream()
                .filter(t->t.getTicketStatus().equals(TicketStatus.IN_PROGRESS))
                .toList();

        StatDto statDto1 = new StatDto(
                "OPEN TICKETS",
                openTickets.size()
        );

        StatDto statDto2 = new StatDto(
                "IN_PROGRESS TICKETS",
                inProcessTickets.size()
        );

        StatDto statDto3 = new StatDto(
                "CLOSED TICKETS",
                closedTickets.size()
        );

        return List.of(statDto1,statDto2,statDto3);
    }

    public List<StatDtoV2> getTicketStatsV2(String username) {
        return ticketRepository.getTicketByCustomerUsernameV2(username);
    }

    public List<TicketDto> getTicketByCustomerForAdmin(long customerId) {
        List<Ticket> list = ticketRepository.getTicketByCustomerId(customerId);

        return list
                .stream()
                .map(TicketMapper::maptoTicketDto)
                .toList();
    }
}