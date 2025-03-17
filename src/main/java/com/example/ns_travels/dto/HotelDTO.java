package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class HotelDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private double pricePerNight;
    private double rating;
    private String contactInfo;

    public HotelDTO() {
    }

    public HotelDTO(Long id, String name, String location, String description, double pricePerNight, double rating, String contactInfo) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.rating = rating;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", rating=" + rating +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
