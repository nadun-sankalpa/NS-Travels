package com.example.ns_travels.service;

import com.example.ns_travels.dto.UniversityDTO;

import java.util.List;

public interface UniversityService {
    void save(UniversityDTO universityDTO);
    void update(UniversityDTO universityDTO);
    void delete(Long id);
    UniversityDTO getUniversityById(Long id);
    List<UniversityDTO> getAllUniversities();
    List<UniversityDTO> getUniversitiesByRanking(int minRanking);
}
