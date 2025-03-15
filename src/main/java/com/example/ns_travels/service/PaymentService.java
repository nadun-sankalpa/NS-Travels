package com.example.ns_travels.service;

import com.example.ns_travels.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    void save(PaymentDTO paymentDTO);
    void update(PaymentDTO paymentDTO);
    void delete(Long id);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getAllPayments();
    List<PaymentDTO> getPaymentsByUserId(Long userId);
}
