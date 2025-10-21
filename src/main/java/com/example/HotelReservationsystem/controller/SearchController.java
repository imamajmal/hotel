package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.entity.Hotel;
import com.example.HotelReservationsystem.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    // 👥 Accessible by USER or ADMIN
    @GetMapping("/available")
    public ResponseEntity<List<Hotel>> searchHotels(
            @RequestParam String city,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate
    ) {
        return ResponseEntity.ok(searchService.searchAvailableHotels(city, checkInDate, checkOutDate));
    }
}

