package com.example.ns_travels.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String fullName;

        @Column(nullable = false)
        private String emailAddress;

        @Column(nullable = false)
        private String phoneNumber;

        @Column(nullable = false)
        private String chosenPackage;

        @Column(nullable = false, name = "travel_package_id") // Added field
        private Long travelPackageId;

        @Column(nullable = false)
        private LocalDate travelDate;

        @Column(nullable = false)
        private int numberOfGuests;

        private String additionalRequests;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        // Constructors
        public Booking() {
        }

        public Booking(String fullName, String emailAddress, String phoneNumber,
                       String chosenPackage, Long travelPackageId, LocalDate travelDate,
                       int numberOfGuests, String additionalRequests, User user) {
                this.fullName = fullName;
                this.emailAddress = emailAddress;
                this.phoneNumber = phoneNumber;
                this.chosenPackage = chosenPackage;
                this.travelPackageId = travelPackageId; // Added to constructor
                this.travelDate = travelDate;
                this.numberOfGuests = numberOfGuests;
                this.additionalRequests = additionalRequests;
                this.user = user;
        }

        // Getters and Setters
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getFullName() {
                return fullName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        public String getEmailAddress() {
                return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
                this.emailAddress = emailAddress;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public String getChosenPackage() {
                return chosenPackage;
        }

        public void setChosenPackage(String chosenPackage) {
                this.chosenPackage = chosenPackage;
        }

        // Added getter/setter for travelPackageId
        public Long getTravelPackageId() {
                return travelPackageId;
        }

        public void setTravelPackageId(Long travelPackageId) {
                this.travelPackageId = travelPackageId;
        }

        public LocalDate getTravelDate() {
                return travelDate;
        }

        public void setTravelDate(LocalDate travelDate) {
                this.travelDate = travelDate;
        }

        public int getNumberOfGuests() {
                return numberOfGuests;
        }

        public void setNumberOfGuests(int numberOfGuests) {
                this.numberOfGuests = numberOfGuests;
        }

        public String getAdditionalRequests() {
                return additionalRequests;
        }

        public void setAdditionalRequests(String additionalRequests) {
                this.additionalRequests = additionalRequests;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        @Override
        public String toString() {
                return "Booking{" +
                        "id=" + id +
                        ", fullName='" + fullName + '\'' +
                        ", emailAddress='" + emailAddress + '\'' +
                        ", phoneNumber='" + phoneNumber + '\'' +
                        ", chosenPackage='" + chosenPackage + '\'' +
                        ", travelPackageId=" + travelPackageId + // Added to toString
                        ", travelDate=" + travelDate +
                        ", numberOfGuests=" + numberOfGuests +
                        ", additionalRequests='" + additionalRequests + '\'' +
                        ", user=" + user +
                        '}';
        }
}