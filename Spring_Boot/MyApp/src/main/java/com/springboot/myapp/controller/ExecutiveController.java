package com.springboot.myapp.controller;

import com.springboot.myapp.dto.ExecutivePageResDto;
import com.springboot.myapp.dto.ExecutiveReqDto;
import com.springboot.myapp.enums.JobTitle;
import com.springboot.myapp.model.Executive;
import com.springboot.myapp.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/executive")
public class ExecutiveController {
    private final ExecutiveService executiveService;

    @PostMapping("/add")
    public ResponseEntity<?> addExecutive(@Valid @RequestBody ExecutiveReqDto executiveReqDto){
        executiveService.addExecutive(executiveReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-all")
    public ExecutivePageResDto getAllExecutives(@RequestParam (value="page", required = false, defaultValue = "0") int page,
                                                @RequestParam (value = "size", required = false, defaultValue = "0") int size){
        return executiveService.getAllExecutives(page,size);
    }

    @GetMapping("/get/{id}")
    public Executive getExecutiveById(@PathVariable long id){
        return executiveService.getExecutiveById(id);
    }

    @GetMapping("/get/filter/{jobTitle}")
    public List<Executive> getExecutiveByJobTitle(@PathVariable JobTitle jobTitle){
        return executiveService.getExecutiveByJobTitle(jobTitle);
    }
}