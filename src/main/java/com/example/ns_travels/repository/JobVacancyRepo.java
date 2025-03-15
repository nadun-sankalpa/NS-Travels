package com.example.ns_travels.repository;

import com.example.ns_travels.entity.JobVacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobVacancyRepo extends JpaRepository<JobVacancy, Long> {
    List<JobVacancy> findByLocation(String location);
}
