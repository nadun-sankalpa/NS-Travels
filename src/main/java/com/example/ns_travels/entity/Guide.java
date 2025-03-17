package com.example.ns_travels.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "guides")
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String language;
    @Column(nullable = false)
    private int experience;
    @Column(nullable = false)
    private double rating;
    @Column(nullable = false)
    private String contactInfo;
    @Column(nullable = false)
    private boolean availability;

    public Guide() {
    }

    public Guide(Long id, String name, String language, int experience, double rating, String contactInfo, boolean availability) {
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
        return "Guide{" +
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

