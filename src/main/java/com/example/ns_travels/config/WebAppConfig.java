package com.example.ns_travels.config;

import com.example.ns_travels.dto.BookingDTO;
import com.example.ns_travels.entity.Booking;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig {

    // ModelMapper bean to handle mapping between entities and DTOs
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Set matching strategy to STRICT to avoid ambiguity
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Explicit mapping from Booking to BookingDTO
        TypeMap<Booking, BookingDTO> bookingTypeMap = modelMapper.createTypeMap(Booking.class, BookingDTO.class);
        bookingTypeMap.addMapping(src -> src.getUser().getEmail(), BookingDTO::setUserEmail); // Mapping User's email
        bookingTypeMap.addMapping(src -> src.getTravelPackage().getName(), BookingDTO::setPackageName); // Mapping TravelPackage's name
        bookingTypeMap.addMapping(Booking::getNumberOfGuests, BookingDTO::setNumberOfGuests); // Mapping Number of Guests
        bookingTypeMap.addMapping(Booking::getTravelDate, BookingDTO::setTravelDate); // Mapping Travel Date
        bookingTypeMap.addMapping(Booking::getAdditionalRequests, BookingDTO::setAdditionalRequests); // Mapping Additional Requests
        bookingTypeMap.addMapping(Booking::getStatus, BookingDTO::setStatus); // Mapping Booking Status

        // If you intend to map the guestNames, you would need to handle it here.
        // Since the Booking entity doesn't directly have guestNames, this mapping
        // would likely involve a custom logic or a different source field.

        return modelMapper;
    }

    // CORS configuration for handling cross-origin requests
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Allow all origins and all methods
                registry.addMapping("/**")
                        .allowedOrigins("*") // Allow any origin to make requests
                        .allowedMethods("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
            }
        };
    }
}