package com.example.ns_travels.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Booking {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Entity
    public class Booking {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "hotel_id")
        private Hotel hotel;

        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private double totalPrice;

        @Enumerated(EnumType.STRING)
        private BookingStatus status;
    }

}
