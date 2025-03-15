package com.example.ns_travels.service;

import com.example.ns_travels.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    void save(BookingDTO bookingDTO);
    void update(BookingDTO bookingDTO);
    void delete(Long id);
    BookingDTO getBookingById(Long id);
    List<BookingDTO> getAllBookings();
    List<BookingDTO> getBookingsByUserId(Long userId);
}
