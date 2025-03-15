package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO {
    private Long id;
    private String type;
    private String model;
    private int capacity;
    private double pricePerDay;
    private boolean availability;
}
