package com.example.ns_travels.repository;

import com.example.ns_travels.entity.TravelPackages;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPackagesRepo extends JpaRepository<TravelPackages, Long> {
    List<TravelPackages> findByBudgetBetween(Double minBudget, Double maxBudget);

    TravelPackages findByName(String name);
}
