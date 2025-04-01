package com.example.ns_travels.controller;

import com.example.ns_travels.dto.TravelPackagesDTO;
import com.example.ns_travels.service.TravelPackagesService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel-packages")
@CrossOrigin(origins = "*")
public class TravelPackagesController {

    private final TravelPackagesService travelPackagesService;

    public TravelPackagesController(TravelPackagesService travelPackagesService) {
        this.travelPackagesService = travelPackagesService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseUtil> save(@RequestBody TravelPackagesDTO travelPackageDTO) {
        try {
            if (travelPackageDTO.getPrice() == 0) {
                throw new IllegalArgumentException("Price cannot be zero");
            }
            travelPackagesService.save(travelPackageDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseUtil(201, "Travel package saved successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseUtil> update(@RequestBody TravelPackagesDTO travelPackageDTO) {
        try {
            travelPackagesService.update(travelPackageDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Travel package updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseUtil> delete(@PathVariable Long id) {
        try {
            travelPackagesService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Travel package deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUtil> getTravelPackageById(@PathVariable Long id) {
        try {
            TravelPackagesDTO travelPackageDTO = travelPackagesService.getTravelPackageById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Travel package fetched successfully", travelPackageDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseUtil> getAllTravelPackages() {
        try {
            List<TravelPackagesDTO> travelPackages = travelPackagesService.getAllTravelPackages();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Travel packages fetched successfully", travelPackages));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    @GetMapping("/by-budget")
    public ResponseEntity<ResponseUtil> getTravelPackagesByBudget(@RequestParam Double minBudget, @RequestParam Double maxBudget) {
        try {
            List<TravelPackagesDTO> travelPackages = travelPackagesService.getTravelPackagesByBudget(minBudget, maxBudget);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Travel packages fetched successfully", travelPackages));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }
}
