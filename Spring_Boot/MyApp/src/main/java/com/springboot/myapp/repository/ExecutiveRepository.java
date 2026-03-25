package com.springboot.myapp.repository;

import com.springboot.myapp.model.Executive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutiveRepository extends JpaRepository<Executive,Long> {
}