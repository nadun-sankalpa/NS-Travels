package com.example.ns_travels.repository;

import com.example.ns_travels.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);
}
