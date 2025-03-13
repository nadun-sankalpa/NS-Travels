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
        @Column(nullable = false)
        private User user;

        @ManyToOne
        @JoinColumn(name = "hotel_id")
        @Column(nullable = false)
        private Hotel hotel;
        @Column(nullable = false)
        private LocalDate checkInDate;
        @Column(nullable = false)
        private LocalDate checkOutDate;
        @Column(nullable = false)
        private double totalPrice;

        @Enumerated(EnumType.STRING)
        private BookingStatus status;
    }

}
