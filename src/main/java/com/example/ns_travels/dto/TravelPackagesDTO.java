package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TravelPackagesDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int duration;
    private String includedServices;
}
