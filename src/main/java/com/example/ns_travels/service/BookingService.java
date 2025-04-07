package com.example.ns_travels.service;

import com.example.ns_travels.dto.BookingDTO;
import com.example.ns_travels.entity.Booking;

import java.util.List;

public interface BookingService {

    void save(BookingDTO bookingDTO);
    void save(Booking booking);
    void delete(Long id);
    void update(Long id, BookingDTO bookingDTO);
    List<BookingDTO> getAll();
    List<BookingDTO> getByUserId(Long userId);
}
