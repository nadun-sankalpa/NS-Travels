package com.example.ns_travels.repository;

import com.example.ns_travels.entity.TravelPackages;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPackagesRepo extends JpaRepository<TravelPackagesRepo, Long> {
    List<TravelPackages> findByBudgetBetween(Double minBudget, Double maxBudget);
}
