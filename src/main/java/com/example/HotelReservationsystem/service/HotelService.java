package com.example.HotelReservationsystem.service;

import com.example.HotelReservationsystem.entity.Hotel;
import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    Hotel getHotelById(Long id);
    void saveHotel(Hotel hotel);
    void updateHotel(Long id, Hotel hotel);
    void deleteHotel(Long id);
}


