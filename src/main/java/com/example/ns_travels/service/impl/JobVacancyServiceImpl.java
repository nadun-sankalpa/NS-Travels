package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.JobVacancyDTO;
import com.example.ns_travels.entity.JobVacancy;
import com.example.ns_travels.repository.JobVacancyRepo;
import com.example.ns_travels.service.JobVacancyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobVacancyServiceImpl implements JobVacancyService {
    @Autowired
    private JobVacancyRepo jobVacancyRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(JobVacancyDTO jobVacancyDTO) {
        JobVacancy jobVacancy = modelMapper.map(jobVacancyDTO, JobVacancy.class);
        jobVacancyRepo.save(jobVacancy);
    }

    @Override
    public void update(JobVacancyDTO jobVacancyDTO) {
        Optional<JobVacancy> optJobVacancy = jobVacancyRepo.findById(jobVacancyDTO.getId());
        if (optJobVacancy.isEmpty()) {
            throw new RuntimeException("Job vacancy not found with ID: " + jobVacancyDTO.getId());
        }

        JobVacancy jobVacancy = modelMapper.map(jobVacancyDTO, JobVacancy.class);
        jobVacancyRepo.save(jobVacancy);
    }

    @Override
    public void delete(Long id) {
        if (!jobVacancyRepo.existsById(id)) {
            throw new RuntimeException("Job vacancy not found with ID: " + id);
        }
        jobVacancyRepo.deleteById(id);
    }

    @Override
    public JobVacancyDTO getJobVacancyById(Long id) {
        Optional<JobVacancy> jobVacancy = jobVacancyRepo.findById(id);
        if (jobVacancy.isEmpty()) {
            throw new RuntimeException("Job vacancy not found with ID: " + id);
        }
        return modelMapper.map(jobVacancy.get(), JobVacancyDTO.class);
    }

    @Override
    public List<JobVacancyDTO> getAllJobVacancies() {
        List<JobVacancy> jobVacancies = jobVacancyRepo.findAll();
        return jobVacancies.stream()
                .map(job -> modelMapper.map(job, JobVacancyDTO.class))
                .toList();
    }
}

