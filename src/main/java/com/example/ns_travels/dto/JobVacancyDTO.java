package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobVacancyDTO {
    private Long id;
    private String title;
    private String company;
    private String location;
    private String salaryRange;
    private String requirements;
    private String contactInfo;
}
