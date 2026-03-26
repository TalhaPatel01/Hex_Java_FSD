package com.springboot.myapp.service;

import com.springboot.myapp.exception.ResourceNotFoundException;
import com.springboot.myapp.model.Plan;
import com.springboot.myapp.repository.PlanRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Plan getById(long planId) {
        return planRepository.findById(planId)
                .orElseThrow(()->new ResourceNotFoundException("Plan id invalid"));
    }
}