package com.example.ns_travels.controller;

import com.example.ns_travels.dto.DestinationDTO;
import com.example.ns_travels.service.DestinationService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    // Get destination by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUtil> getDestinationById(@PathVariable Long id) {
        try {
            DestinationDTO destinationDTO = destinationService.getDestinationById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Destination fetched successfully", destinationDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Get all destinations
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUtil> getAllDestinations() {
        try {
            List<DestinationDTO> destinations = destinationService.getAllDestinations();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Destinations fetched successfully", destinations));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Add a new destination
    @PostMapping("/addDestination")
    public ResponseEntity<ResponseUtil> addDestination(@RequestBody DestinationDTO destinationDTO) {
        try {
            destinationService.save(destinationDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseUtil(201, "Destination added successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Update destination
    @PutMapping("/updateDestination")
    public ResponseEntity<ResponseUtil> updateDestination(@RequestBody DestinationDTO destinationDTO) {
        try {
            destinationService.update(destinationDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Destination updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Delete destination
    @DeleteMapping("/deleteDestination/{id}")
    public ResponseEntity<ResponseUtil> deleteDestination(@PathVariable Long id) {
        try {
            destinationService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Destination deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }
}
