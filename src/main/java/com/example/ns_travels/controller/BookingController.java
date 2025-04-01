package com.example.ns_travels.controller;

import com.example.ns_travels.dto.BookingDTO;
import com.example.ns_travels.service.BookingService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Get booking by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUtil> getBookingById(@PathVariable Long id) {
        try {
            BookingDTO bookingDTO = bookingService.getBookingById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Booking fetched successfully", bookingDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Get all bookings
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUtil> getAllBookings() {
        try {
            List<BookingDTO> bookings = bookingService.getAllBookings();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Bookings fetched successfully", bookings));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Add a new booking
    @PostMapping("/addBooking")
    public ResponseEntity<ResponseUtil> addBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            bookingService.save(bookingDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseUtil(201, "Booking added successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Update booking
    @PutMapping("/updateBooking")
    public ResponseEntity<ResponseUtil> updateBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            bookingService.updateBooking(bookingDTO.getId(), bookingDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Booking updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }

    // Delete booking
    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<ResponseUtil> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseUtil(200, "Booking deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, e.getMessage(), null));
        }
    }
}