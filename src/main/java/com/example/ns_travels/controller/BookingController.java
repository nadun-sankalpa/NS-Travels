package com.example.ns_travels.controller;

import com.example.ns_travels.dto.BookingDTO;
import com.example.ns_travels.dto.ResponseDTO;
import com.example.ns_travels.dto.TravelPackagesDTO;
import com.example.ns_travels.dto.UserDTO;
import com.example.ns_travels.entity.Booking;
import com.example.ns_travels.service.BookingService;
import com.example.ns_travels.service.TravelPackagesService;
import com.example.ns_travels.service.UserService;
import com.example.ns_travels.service.impl.BookingServiceImpl;
import com.example.ns_travels.service.impl.EmailServiceImpl;
import com.example.ns_travels.service.impl.UserServiceImpl;
import com.example.ns_travels.util.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;
    private final BookingServiceImpl bookingServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final TravelPackagesService packageService;

    @Autowired
    public BookingController(BookingService bookingService,
                             BookingServiceImpl bookingServiceImpl,
                             UserServiceImpl userServiceImpl,
                             UserService userService,
                             EmailServiceImpl emailService,
                             TravelPackagesService packageService) {
        this.bookingService = bookingService;
        this.bookingServiceImpl = bookingServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.userService = userService;
        this.emailService = emailService;
        this.packageService = packageService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")  // ✅ Requires role in token
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody BookingDTO bookingDTO) {
        System.out.println("Booking save controller");

        UserDTO userDto = userServiceImpl.findByEmail(bookingDTO.getUserEmail());
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(VarList.Bad_Request, "Your email is not registered with us", null));
        }

        TravelPackagesDTO packageDTO = packageService.getPackageByName(bookingDTO.getPackageName());
        if (packageDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(VarList.Bad_Request, "Package not found with the name: " + bookingDTO.getPackageName(), null));
        }

        bookingDTO.setUser(userDto);
        bookingDTO.setTravelPackage(packageDTO);
        bookingDTO.setStatus(Booking.BookingStatus.PENDING);

        bookingServiceImpl.save(bookingDTO);

        emailService.sendBookingConfirmationEmail(
                bookingDTO.getUserEmail(),
                "Your Booking is Confirmed – NS TRAVELS 🌿\n",
                "Hi " + userDto.getUsername() + ",\n\n" +
                        "Your booking has been confirmed successfully. Here are the details:\n\n" +
                        "📅 Travel Date: " + bookingDTO.getTravelDate() + "\n" +
                        "📍 Location: No.42, Karapitiya, Galle\n" +
                        "📞 Contact: 076 209 9693\n\n" +
                        "What to Expect:\n" +
                        "Our expert team is ready to provide you with a relaxing and professional experience. If you have any questions before your travel, feel free to call us!\n\n" +
                        "ExploreLanka Team\n" +
                        "📍 No.42, Karapitiya, Galle\n" +
                        "📞 076 209 9693"
        );

        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Booking Saved Successfully", bookingDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllBookings() {
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", bookingService.getAll()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Booking deleted successfully", null));
    }
}
