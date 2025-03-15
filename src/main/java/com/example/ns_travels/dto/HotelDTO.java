package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private double pricePerNight;
    private double rating;
    private String contactInfo;
}
