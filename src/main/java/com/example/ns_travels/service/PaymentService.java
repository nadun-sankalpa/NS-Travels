package com.example.ns_travels.service;

import com.example.ns_travels.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    // Save a new payment
    void save(PaymentDTO paymentDTO);

    // Update an existing payment
    void update(PaymentDTO paymentDTO);

    // Delete a payment by ID
    void delete(Long id);

    // Get a single payment by ID
    PaymentDTO getPaymentById(Long id);

    // Get all payments
    List<PaymentDTO> getAllPayments();

    // Get all payments for a specific user
    List<PaymentDTO> getPaymentsByUserId(Long userId);
}
