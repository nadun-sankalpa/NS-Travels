package com.example.ns_travels.dto;

import com.example.ns_travels.entity.Booking;
import java.time.LocalDate;

public class BookingDTO {

    private Long id;
    private TravelPackagesDTO travelPackage;
    private String packageName;
    private UserDTO user;
    private String userEmail;
    private LocalDate travelDate;
    private int numberOfGuests;
    private String additionalRequests;
    private Booking.BookingStatus status;

    public BookingDTO() {
    }

    public BookingDTO(Long id, TravelPackagesDTO travelPackage, String packageName, UserDTO user, String userEmail, LocalDate travelDate, int numberOfGuests, String additionalRequests, Booking.BookingStatus status) {
        this.id = id;
        this.travelPackage = travelPackage;
        this.packageName = packageName;
        this.user = user;
        this.userEmail = userEmail;
        this.travelDate = travelDate;
        this.numberOfGuests = numberOfGuests;
        this.additionalRequests = additionalRequests;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TravelPackagesDTO getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackagesDTO travelPackage) {
        this.travelPackage = travelPackage;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public Booking.BookingStatus getStatus() {
        return status;
    }

    public void setStatus(Booking.BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", travelPackage=" + travelPackage +
                ", packageName='" + packageName + '\'' +
                ", user=" + user +
                ", userEmail='" + userEmail + '\'' +
                ", travelDate=" + travelDate +
                ", numberOfGuests=" + numberOfGuests +
                ", additionalRequests='" + additionalRequests + '\'' +
                ", status=" + status +
                '}';
    }
}
