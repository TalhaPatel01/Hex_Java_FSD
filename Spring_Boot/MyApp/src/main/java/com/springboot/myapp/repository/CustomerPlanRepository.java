package com.springboot.myapp.repository;

import com.springboot.myapp.model.CustomerPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPlanRepository extends JpaRepository<CustomerPlan,Long> {
}