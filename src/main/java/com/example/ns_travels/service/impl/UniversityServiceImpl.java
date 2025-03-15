package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.UniversityDTO;
import com.example.ns_travels.entity.University;
import com.example.ns_travels.repository.UniversityRepo;
import com.example.ns_travels.service.UniversityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private UniversityRepo universityRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(UniversityDTO universityDTO) {
        University university = modelMapper.map(universityDTO, University.class);
        universityRepo.save(university);
    }

    @Override
    public void update(UniversityDTO universityDTO) {
        Optional<University> optUniversity = universityRepo.findById(universityDTO.getId());
        if (optUniversity.isEmpty()) {
            throw new RuntimeException("University not found with ID: " + universityDTO.getId());
        }

        University university = modelMapper.map(universityDTO, University.class);
        universityRepo.save(university);
    }

    @Override
    public void delete(Long id) {
        if (!universityRepo.existsById(id)) {
            throw new RuntimeException("University not found with ID: " + id);
        }
        universityRepo.deleteById(id);
    }

    @Override
    public UniversityDTO getUniversityById(Long id) {
        Optional<University> university = universityRepo.findById(id);
        if (university.isEmpty()) {
            throw new RuntimeException("University not found with ID: " + id);
        }
        return modelMapper.map(university.get(), UniversityDTO.class);
    }

    @Override
    public List<UniversityDTO> getAllUniversities() {
        List<University> universities = universityRepo.findAll();
        return universities.stream()
                .map(university -> modelMapper.map(university, UniversityDTO.class))
                .toList();
    }

    @Override
    public List<UniversityDTO> getUniversitiesByRanking(int minRanking) {
        List<University> universities = universityRepo.findByRankingGreaterThanEqual(minRanking);
        return universities.stream()
                .map(university -> modelMapper.map(university, UniversityDTO.class))
                .toList();
    }
}

