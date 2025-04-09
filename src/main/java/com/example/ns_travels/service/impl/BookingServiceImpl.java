package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.BookingDTO;
import com.example.ns_travels.dto.ResponseDTO;
import com.example.ns_travels.entity.Booking;
import com.example.ns_travels.entity.TravelPackages;
import com.example.ns_travels.entity.User;
import com.example.ns_travels.repository.BookingRepository;
import com.example.ns_travels.repository.TravelPackagesRepo;
import com.example.ns_travels.repository.UsersRepo;
import com.example.ns_travels.service.BookingService;
import com.example.ns_travels.service.EmailService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UsersRepo userRepository;

    @Autowired
    private TravelPackagesRepo travelPackageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    private static final Logger logger = Logger.getLogger(BookingServiceImpl.class.getName());

    @Override
    public void save(BookingDTO bookingDTO) {
        System.out.println("save booking");

        TravelPackages travelPackage = modelMapper.map(bookingDTO.getTravelPackage(), TravelPackages.class);
        User user = modelMapper.map(bookingDTO.getUser(), User.class);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTravelPackage(travelPackage);
        booking.setStatus(bookingDTO.getStatus());
        booking.setAdditionalRequests(bookingDTO.getAdditionalRequests());
        booking.setNumberOfGuests(bookingDTO.getNumberOfGuests());
        booking.setTravelDate(bookingDTO.getTravelDate());
        booking.setUserEmail(bookingDTO.getUserEmail());
        booking.setUserName(bookingDTO.getUser().getUsername());

        bookingRepository.save(booking);
    }

    @Override
    public List<BookingDTO> getAll() {
        List<Booking> bookings = bookingRepository.findAll();
        Type listType = new TypeToken<List<BookingDTO>>() {}.getType();
        return modelMapper.map(bookings, listType); // This is line 68
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public List<BookingDTO> getByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        Type listType = new TypeToken<List<BookingDTO>>() {}.getType();
        return modelMapper.map(bookings, listType);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public void update(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found."));

        existingBooking.setStatus(Booking.BookingStatus.valueOf(String.valueOf(bookingDTO.getStatus())));

        bookingRepository.save(existingBooking);
    }
}