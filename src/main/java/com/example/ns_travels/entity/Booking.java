package com.example.ns_travels.entity;

import com.example.ns_travels.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)  // Corrected
        private User user;

        @ManyToOne
        @JoinColumn(name = "hotel_id", nullable = false) // Corrected
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
