package com.example.ns_travels.controller;

import com.example.ns_travels.dto.BookingDTO;
import com.example.ns_travels.entity.User;
import com.example.ns_travels.repository.UsersRepo;
import com.example.ns_travels.service.BookingService;
import com.example.ns_travels.service.TravelPackagesService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;
    private final TravelPackagesService packageService;
    private final UsersRepo userRepository;

    // Constructor Injection
    public BookingController(BookingService bookingService, TravelPackagesService packageService, UsersRepo userRepository) {
        this.bookingService = bookingService;
        this.packageService = packageService;
        this.userRepository = userRepository;
    }

    // ✅ Get Booking by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUtil> getBookingById(@PathVariable Long id) {
        try {
            BookingDTO bookingDTO = bookingService.getBookingById(id);
            return ResponseEntity.ok(new ResponseUtil(200, "Booking fetched successfully", bookingDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error fetching booking: " + e.getMessage(), null));
        }
    }

    // ✅ Get All Bookings
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUtil> getAllBookings() {
        try {
            List<BookingDTO> bookings = bookingService.getAllBookings();
            return ResponseEntity.ok(new ResponseUtil(200, "Bookings fetched successfully", bookings));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error fetching bookings: " + e.getMessage(), null));
        }
    }

    // ✅ Add a New Booking
    @PostMapping("/addBooking")
    public ResponseEntity<ResponseUtil> addBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            System.out.println("Received booking data: " + bookingDTO);

            if (bookingDTO.getUserId() == null) {
                return ResponseEntity.badRequest().body(new ResponseUtil(400, "User ID is required", null));
            }

            Optional<User> user = userRepository.findById(bookingDTO.getUserId());
            if (user.isEmpty()) {
                return ResponseEntity.badRequest().body(new ResponseUtil(400, "User not found", null));
            }

            bookingService.save(bookingDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseUtil(201, "Booking added successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error saving booking: " + e.getMessage(), null));
        }
    }


    // ✅ Update a Booking
    @PutMapping("/updateBooking")
    public ResponseEntity<ResponseUtil> updateBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            if (bookingDTO.getId() == null) {
                return ResponseEntity.badRequest().body(new ResponseUtil(400, "Booking ID is required", null));
            }
            bookingService.updateBooking(bookingDTO.getId(), bookingDTO);
            return ResponseEntity.ok(new ResponseUtil(200, "Booking updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error updating booking: " + e.getMessage(), null));
        }
    }

    // ✅ Delete a Booking
    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<ResponseUtil> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.ok(new ResponseUtil(200, "Booking deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error deleting booking: " + e.getMessage(), null));
        }
    }

    // ✅ Check if a Travel Package Exists
    @GetMapping("/packages/exists/{id}")
    public ResponseEntity<Boolean> packageExists(@PathVariable Long id) {
        return ResponseEntity.ok(packageService.existsById(id));
    }
}
