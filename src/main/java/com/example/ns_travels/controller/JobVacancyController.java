package com.example.ns_travels.controller;

import com.example.ns_travels.dto.JobVacancyDTO;
import com.example.ns_travels.service.JobVacancyService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-vacancies")
public class JobVacancyController {

    private final JobVacancyService jobVacancyService;

    public JobVacancyController(JobVacancyService jobVacancyService) {
        this.jobVacancyService = jobVacancyService;
    }

    // Get job vacancy by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUtil> getJobVacancyById(@PathVariable Long id) {
        try {
            JobVacancyDTO jobVacancyDTO = jobVacancyService.getJobVacancyById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Job vacancy fetched successfully", jobVacancyDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Get all job vacancies
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUtil> getAllJobVacancies() {
        try {
            List<JobVacancyDTO> jobVacancies = jobVacancyService.getAllJobVacancies();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Job vacancies fetched successfully", jobVacancies));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Add a new job vacancy
    @PostMapping("/addJobVacancy")
    public ResponseEntity<ResponseUtil> addJobVacancy(@RequestBody JobVacancyDTO jobVacancyDTO) {
        try {
            jobVacancyService.save(jobVacancyDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseUtil(201, "Job vacancy added successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Update job vacancy
    @PutMapping("/updateJobVacancy")
    public ResponseEntity<ResponseUtil> updateJobVacancy(@RequestBody JobVacancyDTO jobVacancyDTO) {
        try {
            jobVacancyService.update(jobVacancyDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Job vacancy updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Delete job vacancy
    @DeleteMapping("/deleteJobVacancy/{id}")
    public ResponseEntity<ResponseUtil> deleteJobVacancy(@PathVariable Long id) {
        try {
            jobVacancyService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Job vacancy deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }
}
