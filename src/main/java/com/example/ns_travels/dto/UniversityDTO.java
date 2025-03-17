package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UniversityDTO {
    private Long id;
    private String name;
    private String location;
    private double ranking;
    private String contactInfo;

    public UniversityDTO() {
    }

    public UniversityDTO(Long id, String name, String location, double ranking, String contactInfo) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.ranking = ranking;
        this.contactInfo = contactInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRanking() {
        return ranking;
    }

    public void setRanking(double ranking) {
        this.ranking = ranking;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "UniversityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", ranking=" + ranking +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
