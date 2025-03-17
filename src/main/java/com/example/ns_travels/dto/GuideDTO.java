package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class GuideDTO {
    private Long id;
    private String name;
    private String language;
    private int experience;
    private double rating;
    private String contactInfo;
    private boolean availability;

    public GuideDTO() {
    }

    public GuideDTO(Long id, String name, String language, int experience, double rating, String contactInfo, boolean availability) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.experience = experience;
        this.rating = rating;
        this.contactInfo = contactInfo;
        this.availability = availability;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "GuideDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", experience=" + experience +
                ", rating=" + rating +
                ", contactInfo='" + contactInfo + '\'' +
                ", availability=" + availability +
                '}';
    }
}
