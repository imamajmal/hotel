package com.example.HotelReservationsystem.service.impl;

import com.example.HotelReservationsystem.entity.Booking;
import com.example.HotelReservationsystem.entity.Hotel;
import com.example.HotelReservationsystem.repository.BookingRepository;
import com.example.HotelReservationsystem.repository.HotelRepository;
import com.example.HotelReservationsystem.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;

    @Override
    public List<Hotel> searchAvailableHotels(String city, LocalDate checkInDate, LocalDate checkOutDate) {
        // Step 1: Get all hotels in the specified city
        List<Hotel> hotels = hotelRepository.findByCityContainingIgnoreCase(city);

        // Step 2: Filter out hotels that are already booked during the selected date range
        return hotels.stream()
                .filter(hotel -> !bookingRepository
                        .existsByHotelAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
                                hotel, checkOutDate, checkInDate))
                .collect(Collectors.toList());
    }
}

