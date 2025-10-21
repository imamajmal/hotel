package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.entity.Hotel;
import com.example.HotelReservationsystem.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    // 🟢 1️⃣ Get all hotels (accessible by USER & ADMIN)
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    // 🟢 2️⃣ Get a specific hotel by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    // 🔒 3️⃣ Add a new hotel (Admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createHotel(@RequestBody Hotel hotel) {
        hotelService.saveHotel(hotel);
        return ResponseEntity.ok("Hotel added successfully!");
    }

    // 🔒 4️⃣ Update hotel (Admin only)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        hotelService.updateHotel(id, hotel);
        return ResponseEntity.ok("Hotel updated successfully!");
    }

    // 🔒 5️⃣ Delete hotel (Admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Hotel deleted successfully!");
    }
}


