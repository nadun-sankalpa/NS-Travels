package com.example.ns_travels.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "package_id", nullable = false)
        private TravelPackages travelPackage;

        @Column(name = "user_name")
        private String userName;

        @NotBlank(message = "User email is required")
        @Email(message = "Invalid email format")
        @Column(name = "user_email")
        private String userEmail;

        @FutureOrPresent(message = "Travel date must be in the present or future")
        @Column(name = "travel_date")
        private LocalDate travelDate;

        @Min(value = 1, message = "Must have at least 1 guest")
        @Column(name = "number_of_guests")
        private int numberOfGuests;

        @Column(name = "additional_requests")
        private String additionalRequests;

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        private BookingStatus status;

        public enum BookingStatus {
                PENDING, CONFIRMED, CANCELLED
        }

        // No-arg constructor
        public Booking() {
        }

        // All-args constructor
        public Booking(Long id, User user, TravelPackages travelPackage, String userName, String userEmail,
                       LocalDate travelDate, int numberOfGuests, String additionalRequests, BookingStatus status) {
                this.id = id;
                this.user = user;
                this.travelPackage = travelPackage;
                this.userName = userName;
                this.userEmail = userEmail;
                this.travelDate = travelDate;
                this.numberOfGuests = numberOfGuests;
                this.additionalRequests = additionalRequests;
                this.status = status;
        }

        // Getters and setters

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public TravelPackages getTravelPackage() {
                return travelPackage;
        }

        public void setTravelPackage(TravelPackages travelPackage) {
                this.travelPackage = travelPackage;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getUserEmail() {
                return userEmail;
        }

        public void setUserEmail(String userEmail) {
                this.userEmail = userEmail;
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

        public BookingStatus getStatus() {
                return status;
        }

        public void setStatus(BookingStatus status) {
                this.status = status;
        }

        @Override
        public String toString() {
                return "Booking{" +
                        "id=" + id +
                        ", user=" + user +
                        ", travelPackage=" + travelPackage +
                        ", userName='" + userName + '\'' +
                        ", userEmail='" + userEmail + '\'' +
                        ", travelDate=" + travelDate +
                        ", numberOfGuests=" + numberOfGuests +
                        ", additionalRequests='" + additionalRequests + '\'' +
                        ", status=" + status +
                        '}';
        }
}