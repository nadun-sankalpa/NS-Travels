package com.example.ns_travels.service;

import com.example.ns_travels.dto.JobVacancyDTO;

import java.util.List;

public interface JobVacancyService {
    void save(JobVacancyDTO jobVacancyDTO);
    void update(JobVacancyDTO jobVacancyDTO);
    void delete(Long id);
    JobVacancyDTO getJobVacancyById(Long id);
    List<JobVacancyDTO> getAllJobVacancies();
}
