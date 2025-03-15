package com.example.ns_travels.repository;

import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelPackagesRepo extends JpaRepository<TravelPackagesRepo, Long> {
}
