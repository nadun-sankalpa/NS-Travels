package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.BookingDTO;
import com.example.ns_travels.entity.Booking;
import com.example.ns_travels.repository.BookingRepo;
import com.example.ns_travels.service.BookingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final ModelMapper modelMapper;
    private static final Logger logger = Logger.getLogger(BookingServiceImpl.class.getName());

    @Autowired
    public BookingServiceImpl(BookingRepo bookingRepo, ModelMapper modelMapper) {
        this.bookingRepo = bookingRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public BookingDTO save(BookingDTO bookingDTO) {
        logger.info("Saving booking: " + bookingDTO);
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        Booking savedBooking = bookingRepo.save(booking);
        return modelMapper.map(savedBooking, BookingDTO.class);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepo.findAll();
        return modelMapper.map(bookings, new TypeToken<List<BookingDTO>>() {}.getType());
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return modelMapper.map(booking, BookingDTO.class);
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        existingBooking.setFullName(bookingDTO.getFullName());
        existingBooking.setEmailAddress(bookingDTO.getEmailAddress());
        existingBooking.setPhoneNumber(bookingDTO.getPhoneNumber());
        existingBooking.setChosenPackage(bookingDTO.getChosenPackage());
        existingBooking.setTravelDate(bookingDTO.getTravelDate());
        existingBooking.setNumberOfGuests(bookingDTO.getNumberOfGuests());
        existingBooking.setAdditionalRequests(bookingDTO.getAdditionalRequests());

        Booking updatedBooking = bookingRepo.save(existingBooking);
        return modelMapper.map(updatedBooking, BookingDTO.class);
    }

    @Override
    public void deleteBooking(Long id) {
        if (!bookingRepo.existsById(id)) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        bookingRepo.deleteById(id);
    }
}
