package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.ExecutiveReqDto;
import com.springboot.myapp.enums.JobTitle;
import com.springboot.myapp.model.Executive;

public class ExecutiveMapper {
    public static Executive mapToExecutive(ExecutiveReqDto executiveReqDto){
        Executive executive = new Executive();
        executive.setName(executiveReqDto.name());
        executive.setJobTitle(executiveReqDto.jobTitle());
        return executive;
    }
}
