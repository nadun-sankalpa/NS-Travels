package com.example.ns_travels.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Full name is required")
        @Column(nullable = false)
        private String fullName;

        @NotBlank(message = "Email address is required")
        @Column(nullable = false)
        private String emailAddress;

        @NotBlank(message = "Phone number is required")
        @Column(nullable = false)
        private String phoneNumber;

        @NotBlank(message = "Package selection is required")
        @Column(name = "chosen_package", nullable = false)
        private String chosenPackage;

        @NotNull(message = "Package ID is required")
        @Column(name = "travel_package_id", nullable = false)
        private Long travelPackageId;

        @NotNull(message = "Travel date is required")
        @Column(name = "travel_date", nullable = false)
        private LocalDate travelDate;

        @NotNull(message = "Number of guests is required")
        @Min(value = 1, message = "Must have at least 1 guest")
        @Column(name = "number_of_guests", nullable = false)
        private Integer numberOfGuests;

        private String additionalRequests;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        @JsonIgnore
        @NotNull(message = "User ID is required")
        private User user;

        // Constructors
        public Booking() {
        }

        public Booking(String fullName, String emailAddress, String phoneNumber,
                       String chosenPackage, Long travelPackageId, LocalDate travelDate,
                       Integer numberOfGuests, String additionalRequests, User user) {
                this.fullName = fullName;
                this.emailAddress = emailAddress;
                this.phoneNumber = phoneNumber;
                this.chosenPackage = chosenPackage;
                this.travelPackageId = travelPackageId;
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

        public Integer getNumberOfGuests() {
                return numberOfGuests;
        }

        public void setNumberOfGuests(Integer numberOfGuests) {
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
                        ", travelPackageId=" + travelPackageId +
                        ", travelDate=" + travelDate +
                        ", numberOfGuests=" + numberOfGuests +
                        ", additionalRequests='" + additionalRequests + '\'' +
                        ", userId=" + (user != null ? user.getId() : "null") +
                        '}';
        }
}