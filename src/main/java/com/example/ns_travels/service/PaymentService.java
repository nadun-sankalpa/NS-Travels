package com.example.ns_travels.service;

import com.example.ns_travels.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    PaymentDTO savePayment(PaymentDTO paymentDTO);

    PaymentDTO getPaymentById(Long id);

    List<PaymentDTO> getAllPayments();

    void deletePayment(Long id);
}
