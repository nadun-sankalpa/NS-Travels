package com.example.ns_travels.dto;

import lombok.*;

import java.time.LocalDate;

public class BookingDTO {
    private Long id;
    private Long userId;
    private Long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;
    private String status;

    public BookingDTO() {}

    public BookingDTO(Long id, Long userId, Long hotelId, LocalDate checkInDate, LocalDate checkOutDate, double totalPrice, String status) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", hotelId=" + hotelId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
