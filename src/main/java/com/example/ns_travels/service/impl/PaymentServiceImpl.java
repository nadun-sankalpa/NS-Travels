package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.PaymentDTO;
import com.example.ns_travels.entity.Payment;
import com.example.ns_travels.repository.PaymentRepo;
import com.example.ns_travels.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepo paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDTO savePayment(PaymentDTO dto) {
        Payment payment = mapToEntity(dto);
        Payment saved = paymentRepository.save(payment);
        return mapToDTO(saved);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        return optional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // ---------- Mapping Methods ----------
    private Payment mapToEntity(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setCardHolderName(dto.getCardHolderName());
        payment.setCardNumber(dto.getCardNumber());
        payment.setExpirationDate(dto.getExpirationDate());
        payment.setCvv(dto.getCvv());
        return payment;
    }

    private PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setCardHolderName(payment.getCardHolderName());
        dto.setCardNumber(payment.getCardNumber());
        dto.setExpirationDate(payment.getExpirationDate());
        dto.setCvv(payment.getCvv());
        return dto;
    }
}
