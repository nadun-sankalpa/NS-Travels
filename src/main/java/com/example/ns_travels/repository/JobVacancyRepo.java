package com.example.ns_travels.repository;

import com.example.ns_travels.entity.JobVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobVacancyRepo extends JpaRepository<JobVacancy, Long> {
    List<JobVacancy> findByLocation(String location);
}
