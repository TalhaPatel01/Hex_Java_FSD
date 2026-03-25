package com.springboot.myapp.service;

import com.springboot.myapp.dto.ExecutivePageResDto;
import com.springboot.myapp.dto.ExecutiveReqDto;
import com.springboot.myapp.enums.JobTitle;
import com.springboot.myapp.exception.ResourceNotFoundException;
import com.springboot.myapp.mapper.ExecutiveMapper;
import com.springboot.myapp.model.Executive;
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
public class ExecutiveService {
    private final ExecutiveRepository executiveRepository;
    private final TicketRepository ticketRepository;

    public void addExecutive(@Valid ExecutiveReqDto executiveReqDto) {
        //1. map dto to entity
        Executive executive = ExecutiveMapper.mapToExecutive(executiveReqDto);

        //2. add to db
        executiveRepository.save(executive);
    }

    public ExecutivePageResDto getAllExecutives(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Executive> pageTicket = executiveRepository.findAll(pageable);
        long totalRecords = pageTicket.getTotalElements();
        int totalPages = pageTicket.getTotalPages();

        List<Executive> list = pageTicket.stream().toList();

        return new ExecutivePageResDto(
               list,
               totalRecords,
               totalPages
        );
    }

    public Executive getExecutiveById(long id) {
        return executiveRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Invalid id"));
    }

    public List<Executive> getExecutiveByJobTitle(JobTitle jobTitle) {
        if(jobTitle==null){
            return List.of();
        }
        return executiveRepository.getExecutiveByJobTitle(jobTitle);
    }
}