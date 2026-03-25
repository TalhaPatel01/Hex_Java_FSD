package com.springboot.myapp.service;

import com.springboot.myapp.dto.ExecutiveReqDto;
import com.springboot.myapp.mapper.ExecutiveMapper;
import com.springboot.myapp.model.Executive;
import com.springboot.myapp.repository.ExecutiveRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExecutiveService {
    private final ExecutiveRepository executiveRepository;

    public void addExecutive(@Valid ExecutiveReqDto executiveReqDto) {
        //1. map dto to entity
        Executive executive = ExecutiveMapper.mapToExecutive(executiveReqDto);

        //2. add to db
        executiveRepository.save(executive);
    }
}