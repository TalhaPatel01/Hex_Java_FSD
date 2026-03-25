package com.springboot.myapp.repository;

import com.springboot.myapp.enums.JobTitle;
import com.springboot.myapp.model.Executive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExecutiveRepository extends JpaRepository<Executive,Long> {

    @Query("""
            select e from Executive e
            where e.jobTitle = ?1
            """)
    List<Executive> getExecutiveByJobTitle(JobTitle jobTitle);
}