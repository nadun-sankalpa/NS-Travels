package com.example.ns_travels.service;

import com.example.ns_travels.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    void save(HotelDTO hotelDTO);
    void update(HotelDTO hotelDTO);
    void delete(Long id);
    HotelDTO getHotelById(Long id);
    List<HotelDTO> getAllHotels();
}
