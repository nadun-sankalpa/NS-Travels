package com.example.ns_travels.config;

import com.example.ns_travels.dto.BookingDTO;
import com.example.ns_travels.dto.UserDTO;
import com.example.ns_travels.entity.Booking;
import com.example.ns_travels.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Set matching strategy to STRICT to avoid ambiguity
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Explicit mapping from Booking to BookingDTO
        TypeMap<Booking, BookingDTO> bookingTypeMap = modelMapper.createTypeMap(Booking.class, BookingDTO.class);
        bookingTypeMap.addMapping(src -> src.getUser().getEmail(), BookingDTO::setUserEmail);
        bookingTypeMap.addMapping(Booking::getTravelPackage, BookingDTO::setPackageName);
        bookingTypeMap.addMapping(Booking::getNumberOfGuests, BookingDTO::setNumberOfGuests);
        bookingTypeMap.addMapping(Booking::getTravelDate, BookingDTO::setTravelDate);

        return modelMapper;
    }

    // Optional: CORS configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}
