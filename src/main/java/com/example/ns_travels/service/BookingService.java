package com.example.ns_travels.service;

import com.example.ns_travels.dto.BookingDTO;

import java.util.List;

public interface BookingService {

    BookingDTO save(BookingDTO bookingDTO);

    List<BookingDTO> getAllBookings();

    BookingDTO getBookingById(Long id);

    BookingDTO updateBooking(Long id, BookingDTO bookingDTO);

    void deleteBooking(Long id);
}
