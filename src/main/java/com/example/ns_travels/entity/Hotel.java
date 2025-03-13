package com.example.ns_travels.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

public class Hotel {
    @Entity
    @Table(name = "hotels")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Hotel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        private String name;
        @Column(nullable = false)
        private String location;

        @Column(columnDefinition = "TEXT")
        private String description;
        @Column(nullable = false)
        private double pricePerNight;
        @Column(nullable = false)
        private double rating;
        @Column(nullable = false)
        private String contactInfo;
    }
}
