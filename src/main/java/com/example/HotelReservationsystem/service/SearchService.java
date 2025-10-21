package com.example.HotelReservationsystem.service;

import com.example.HotelReservationsystem.entity.Hotel;
import java.time.LocalDate;
import java.util.List;

public interface SearchService {
    List<Hotel> searchAvailableHotels(String city, LocalDate checkInDate, LocalDate checkOutDate);
}
