package com.example.ns_travels.dto;

import com.example.ns_travels.enums.PaymentMethod;
import com.example.ns_travels.enums.PaymentStatus;

public class PaymentDTO {

    private Long id;
    private Long userId;
    private Long travelPackageId;
    private double price;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Long userId, Long travelPackageId, double price,
                      PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.id = id;
        this.userId = userId;
        this.travelPackageId = travelPackageId;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTravelPackageId() {
        return travelPackageId;
    }

    public void setTravelPackageId(Long travelPackageId) {
        this.travelPackageId = travelPackageId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", travelPackageId=" + travelPackageId +
                ", price=" + price +
                ", paymentMethod=" + paymentMethod +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
