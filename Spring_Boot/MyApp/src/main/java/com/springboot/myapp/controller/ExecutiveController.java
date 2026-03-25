package com.springboot.myapp.controller;

import com.springboot.myapp.dto.ExecutiveReqDto;
import com.springboot.myapp.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExecutiveController {
    private final ExecutiveService executiveService;

    @PostMapping("/api/executive/add")
    public ResponseEntity<?> addExecutive(@Valid @RequestBody ExecutiveReqDto executiveReqDto){
        executiveService.addExecutive(executiveReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}