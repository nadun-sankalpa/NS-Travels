package com.example.ns_travels.dto;

import java.time.LocalDate;

public class BookingDTO {

    private Long id;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String chosenPackage;
    private Long travelPackageId;  // ✅ Added travelPackageId
    private LocalDate travelDate;
    private int numberOfGuests;
    private String additionalRequests;
    private Long userId;  // ✅ Added userId

    // Default constructor
    public BookingDTO() {
    }

    // Parameterized constructor
    public BookingDTO(Long id, String fullName, String emailAddress, String phoneNumber,
                      String chosenPackage, Long travelPackageId, LocalDate travelDate,
                      int numberOfGuests, String additionalRequests, Long userId) {
        this.id = id;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.chosenPackage = chosenPackage;
        this.travelPackageId = travelPackageId;  // ✅ Assigning travelPackageId
        this.travelDate = travelDate;
        this.numberOfGuests = numberOfGuests;
        this.additionalRequests = additionalRequests;
        this.userId = userId;  // ✅ Assigning userId
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", chosenPackage='" + chosenPackage + '\'' +
                ", travelPackageId=" + travelPackageId +
                ", travelDate=" + travelDate +
                ", numberOfGuests=" + numberOfGuests +
                ", additionalRequests='" + additionalRequests + '\'' +
                ", userId=" + userId +
                '}';
    }
}
