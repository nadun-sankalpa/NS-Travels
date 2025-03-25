package com.example.ns_travels.controller;

import com.example.ns_travels.dto.UniversityDTO;
import com.example.ns_travels.service.UniversityService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    // Add a new university
    @PostMapping("/add")
    public ResponseEntity<ResponseUtil> addUniversity(@RequestBody UniversityDTO universityDTO) {
        universityService.save(universityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseUtil(201, "University added successfully", null));
    }

    // Update an existing university
    @PutMapping("/update")
    public ResponseEntity<ResponseUtil> updateUniversity(@RequestBody UniversityDTO universityDTO) {
        universityService.update(universityDTO);
        return ResponseEntity.ok(new ResponseUtil(200, "University updated successfully", null));
    }

    // Delete a university
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseUtil> deleteUniversity(@PathVariable Long id) {
        universityService.delete(id);
        return ResponseEntity.ok(new ResponseUtil(200, "University deleted successfully", null));
    }

    // Get all universities
    @GetMapping("/all")
    public ResponseEntity<ResponseUtil> getAllUniversities() {
        List<UniversityDTO> universities = universityService.getAllUniversities();
        return ResponseEntity.ok(new ResponseUtil(200, "Universities retrieved successfully", universities));
    }

    // Get university by ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil> getUniversityById(@PathVariable Long id) {
        UniversityDTO university = universityService.getUniversityById(id);
        if (university != null) {
            return ResponseEntity.ok(new ResponseUtil(200, "University retrieved successfully", university));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseUtil(404, "University not found", null));
        }
    }

    // Get universities by minimum ranking
    @GetMapping("/ranking/{minRanking}")
    public ResponseEntity<ResponseUtil> getUniversitiesByRanking(@PathVariable int minRanking) {
        List<UniversityDTO> universities = universityService.getUniversitiesByRanking(minRanking);
        return ResponseEntity.ok(new ResponseUtil(200, "Universities retrieved successfully", universities));
    }
}
