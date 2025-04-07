package com.example.ns_travels.service;

public interface EmailService {
    void sendBookingConfirmationEmail(String to, String subject, String body);
}
