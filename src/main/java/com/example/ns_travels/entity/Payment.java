package com.example.ns_travels.entity;

import com.example.ns_travels.enums.PaymentMethod;
import com.example.ns_travels.enums.PaymentStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // This is the price that will be assigned from the selected TravelPackage
    @Column(name = "price")
    private double price;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "travel_package_id")
    private TravelPackages travelPackage;

    public Payment() {
    }

    public Payment(Long id, User user, TravelPackages travelPackage, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.id = id;
        this.user = user;
        this.travelPackage = travelPackage;
        this.price = travelPackage != null ? travelPackage.getPrice() : 0.0;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

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

    public TravelPackages getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackages travelPackage) {
        this.travelPackage = travelPackage;
        if (travelPackage != null) {
            this.price = travelPackage.getPrice(); // Automatically set price from selected package
        }
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", user=" + user +
                ", price=" + price +
                ", paymentMethod=" + paymentMethod +
                ", paymentStatus=" + paymentStatus +
                ", travelPackage=" + travelPackage +
                '}';
    }
}
