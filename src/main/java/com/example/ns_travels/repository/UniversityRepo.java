package com.example.ns_travels.repository;

import com.example.ns_travels.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepo extends JpaRepository<University, Long> {
    List<University> findByRankingGreaterThanEqual(double ranking);
}
