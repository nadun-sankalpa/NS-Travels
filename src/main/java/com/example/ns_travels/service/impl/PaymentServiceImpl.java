package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.PaymentDTO;
import com.example.ns_travels.entity.Payment;
import com.example.ns_travels.entity.User;
import com.example.ns_travels.repository.PaymentRepo;
import com.example.ns_travels.repository.UserRepo;
import com.example.ns_travels.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);

        // Validate User
        Optional<User> optUser = userRepo.findById(paymentDTO.getUserId());
        if (optUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + paymentDTO.getUserId());
        }

        // Set User reference
        payment.setUser(optUser.get());

        paymentRepo.save(payment);
    }

    @Override
    public void update(PaymentDTO paymentDTO) {
        Optional<Payment> optPayment = paymentRepo.findById(paymentDTO.getId());
        if (optPayment.isEmpty()) {
            throw new RuntimeException("Payment not found with ID: " + paymentDTO.getId());
        }

        Payment payment = modelMapper.map(paymentDTO, Payment.class);

        // Validate User
        Optional<User> optUser = userRepo.findById(paymentDTO.getUserId());
        if (optUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + paymentDTO.getUserId());
        }

        payment.setUser(optUser.get());

        paymentRepo.save(payment);
    }

    @Override
    public void delete(Long id) {
        if (!paymentRepo.existsById(id)) {
            throw new RuntimeException("Payment not found with ID: " + id);
        }
        paymentRepo.deleteById(id);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepo.findById(id);
        if (payment.isEmpty()) {
            throw new RuntimeException("Payment not found with ID: " + id);
        }
        return modelMapper.map(payment.get(), PaymentDTO.class);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepo.findAll();
        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .toList();
    }

    @Override
    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        List<Payment> payments = paymentRepo.findByUserId(userId);
        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .toList();
    }
}
