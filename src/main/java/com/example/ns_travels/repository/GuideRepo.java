package com.example.ns_travels.repository;

import com.example.ns_travels.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuideRepo extends JpaRepository<Guide,Long> {
    List<Guide> findByLanguage(String language);
}
