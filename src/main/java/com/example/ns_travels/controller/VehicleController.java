package com.example.ns_travels.controller;

import com.example.ns_travels.dto.VehicleDTO;
import com.example.ns_travels.service.VehicleService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Get vehicle by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUtil> getVehicleById(@PathVariable Long id) {
        try {
            VehicleDTO vehicleDTO = vehicleService.getVehicleById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Vehicle fetched successfully", vehicleDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Get all vehicles
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUtil> getAllVehicles() {
        try {
            List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Vehicles fetched successfully", vehicles));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Add a new vehicle
    @PostMapping("/addVehicle")
    public ResponseEntity<ResponseUtil> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.save(vehicleDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseUtil(201, "Vehicle added successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Update vehicle
    @PutMapping("/updateVehicle")
    public ResponseEntity<ResponseUtil> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.update(vehicleDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Vehicle updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Delete vehicle
    @DeleteMapping("/deleteVehicle/{id}")
    public ResponseEntity<ResponseUtil> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Vehicle deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }
}
