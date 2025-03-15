package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.BookingDTO;
import com.example.ns_travels.entity.Booking;
import com.example.ns_travels.entity.Hotel;
import com.example.ns_travels.entity.User;
import com.example.ns_travels.repository.BookingRepo;
import com.example.ns_travels.repository.HotelRepo;
import com.example.ns_travels.repository.UserRepo;
import com.example.ns_travels.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(BookingDTO bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);


        Optional<User> optUser = userRepo.findById(bookingDTO.getUserId());
        if (optUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + bookingDTO.getUserId());
        }


        Optional<Hotel> optHotel = hotelRepo.findById(bookingDTO.getHotelId());
        if (optHotel.isEmpty()) {
            throw new RuntimeException("Hotel not found with ID: " + bookingDTO.getHotelId());
        }


        booking.setUser(optUser.get());
        booking.setHotel(optHotel.get());

        bookingRepo.save(booking);
    }

    @Override
    public void update(BookingDTO bookingDTO) {
        Optional<Booking> optBooking = bookingRepo.findById(bookingDTO.getId());
        if (optBooking.isEmpty()) {
            throw new RuntimeException("Booking not found with ID: " + bookingDTO.getId());
        }

        Booking booking = modelMapper.map(bookingDTO, Booking.class);


        Optional<User> optUser = userRepo.findById(bookingDTO.getUserId());
        if (optUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + bookingDTO.getUserId());
        }


        Optional<Hotel> optHotel = hotelRepo.findById(bookingDTO.getHotelId());
        if (optHotel.isEmpty()) {
            throw new RuntimeException("Hotel not found with ID: " + bookingDTO.getHotelId());
        }

        booking.setUser(optUser.get());
        booking.setHotel(optHotel.get());

        bookingRepo.save(booking);
    }

    @Override
    public void delete(Long id) {
        if (!bookingRepo.existsById(id)) {
            throw new RuntimeException("Booking not found with ID: " + id);
        }
        bookingRepo.deleteById(id);
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Optional<Booking> booking = bookingRepo.findById(id);
        if (booking.isEmpty()) {
            throw new RuntimeException("Booking not found with ID: " + id);
        }
        return modelMapper.map(booking.get(), BookingDTO.class);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepo.findAll();
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .toList();
    }

    @Override
    public List<BookingDTO> getBookingsByUserId(Long userId) {
        List<Booking> bookings = bookingRepo.findByUserId(userId);
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .toList();
    }
}
