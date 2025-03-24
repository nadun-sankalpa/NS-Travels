package com.example.ns_travels.controller;

import com.example.ns_travels.dto.GuideDTO;
import com.example.ns_travels.service.GuideService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guides")
public class GuideController {

    @Autowired
    private GuideService guideService;

    // Get all guides
    @GetMapping("/all")
    public ResponseEntity<ResponseUtil> getAllGuides() {
        List<GuideDTO> guides = guideService.getAllGuides();
        return ResponseEntity.ok(new ResponseUtil(200, "Guides retrieved successfully", guides));
    }

    // Get guide by ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil> getGuideById(@PathVariable Long id) {
        GuideDTO guide = guideService.getGuideById(id);
        if (guide != null) {
            return ResponseEntity.ok(new ResponseUtil(200, "Guide retrieved successfully", guide));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseUtil(404, "Guide not found", null));
        }
    }

    // Add a new guide
    @PostMapping("/add")
    public ResponseEntity<ResponseUtil> addGuide(@RequestBody GuideDTO guideDTO) {
        guideService.save(guideDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseUtil(201, "Guide added successfully", null));
    }

    // Update an existing guide
    @PutMapping("/update")
    public ResponseEntity<ResponseUtil> updateGuide(@RequestBody GuideDTO guideDTO) {
        guideService.update(guideDTO);
        return ResponseEntity.ok(new ResponseUtil(200, "Guide updated successfully", null));
    }

    // Delete a guide
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseUtil> deleteGuide(@PathVariable Long id) {
        guideService.delete(id);
        return ResponseEntity.ok(new ResponseUtil(200, "Guide deleted successfully", null));
    }
}
