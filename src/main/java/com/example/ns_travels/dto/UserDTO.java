package com.example.ns_travels.dto;

import com.example.ns_travels.enums.Role;
import lombok.*;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Role role;

    // If you intend to map the 'bookings' or 'reviews' lists from the User entity
    // to the UserDTO, you need to add corresponding List properties here
    // and ensure their setters accept java.util.List.

    // Example (add these if you want to map the lists):
    // private java.util.List<BookingDTO> bookings;
    // private java.util.List<ReviewDTO> reviews;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password, String email, String phone, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // If you added the List properties above, include their getters and setters:
    // public java.util.List<BookingDTO> getBookings() {
    //     return bookings;
    // }
    //
    // public void setBookings(java.util.List<BookingDTO> bookings) {
    //     this.bookings = bookings;
    // }
    //
    // public java.util.List<ReviewDTO> getReviews() {
    //     return reviews;
    // }
    //
    // public void setReviews(java.util.List<ReviewDTO> reviews) {
    //     this.reviews = reviews;
    // }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                // (Include bookings and reviews in toString if you added them)
                '}';
    }
}