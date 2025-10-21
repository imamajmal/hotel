package com.example.HotelReservationsystem.service;

import com.example.HotelReservationsystem.entity.Booking;
import java.util.List;

public interface BookingService {

    // 🏨 Create a new booking for a specific user & hotel
    Booking createBooking(Long userId, Long hotelId, Booking booking);

    // 👤 Fetch all bookings belonging to a specific user
    List<Booking> getUserBookings(Long userId);

    // ❌ Cancel a booking by ID
    String cancelBooking(Long bookingId);

    // 🧾 Admin use: Fetch all bookings (for reports/dashboard)
    List<Booking> getAllBookings();

    // 🕒 Admin use: Fetch the 5 most recent bookings
    List<Booking> getRecentBookings();

    // 🔍 Get a single booking by its ID (for details view)
    Booking getBookingById(Long bookingId);
}


