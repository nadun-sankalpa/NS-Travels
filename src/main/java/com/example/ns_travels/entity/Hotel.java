package com.example.ns_travels.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
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

        public Hotel() {
        }

        public Hotel(Long id, String name, String location, String description, double pricePerNight, double rating, String contactInfo) {
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
                return "Hotel{" +
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

