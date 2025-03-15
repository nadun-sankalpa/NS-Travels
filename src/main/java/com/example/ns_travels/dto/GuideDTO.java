package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuideDTO {
    private Long id;
    private String name;
    private String language;
    private int experience;
    private double rating;
    private String contactInfo;
    private boolean availability;
}
