package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.entity.Booking;
import com.example.HotelReservationsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // 👥 Accessible by USERS only
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create/{userId}/{hotelId}")
    public ResponseEntity<Booking> createBooking(
            @PathVariable Long userId,
            @PathVariable Long hotelId,
            @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(userId, hotelId, booking));
    }

    // 👥 Accessible by USERS only
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }

    // 👥 Accessible by USERS only
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }
}

