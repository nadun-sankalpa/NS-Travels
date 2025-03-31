package com.example.ns_travels.controller;

import com.example.ns_travels.dto.HotelDTO;
import com.example.ns_travels.service.HotelService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // Get hotel by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUtil> getHotelById(@PathVariable Long id) {
        try {
            HotelDTO hotelDTO = hotelService.getHotelById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Hotel fetched successfully", hotelDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Get all hotels
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUtil> getAllHotels() {
        try {
            List<HotelDTO> hotels = hotelService.getAllHotels();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Hotels fetched successfully", hotels));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Add a new hotel
    @PostMapping("/addHotel")
    public ResponseEntity<ResponseUtil> addHotel(@RequestBody HotelDTO hotelDTO) {
        try {
            hotelService.save(hotelDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseUtil(201, "Hotel added successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Update hotel
    @PutMapping("/updateHotel")
    public ResponseEntity<ResponseUtil> updateHotel(@RequestBody HotelDTO hotelDTO) {
        try {
            hotelService.update(hotelDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Hotel updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Delete hotel
    @DeleteMapping("/deleteHotel/{id}")
    public ResponseEntity<ResponseUtil> deleteHotel(@PathVariable Long id) {
        try {
            hotelService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Hotel deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }
}
