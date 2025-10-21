package com.example.HotelReservationsystem.service.impl;

import com.example.HotelReservationsystem.entity.Booking;
import com.example.HotelReservationsystem.entity.BookingStatus;
import com.example.HotelReservationsystem.entity.Hotel;
import com.example.HotelReservationsystem.entity.User;
import com.example.HotelReservationsystem.repository.BookingRepository;
import com.example.HotelReservationsystem.repository.HotelRepository;
import com.example.HotelReservationsystem.repository.UserRepository;
import com.example.HotelReservationsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    // ✅ Create booking
    @Override
    public Booking createBooking(Long userId, Long hotelId, Booking booking) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        booking.setUser(user);
        booking.setHotel(hotel);
        booking.setStatus(BookingStatus.CONFIRMED);

        return bookingRepository.save(booking);
    }

    // ✅ Get all bookings for a user
    @Override
    public List<Booking> getUserBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUser(user);
    }

    // ✅ Cancel booking
    @Override
    public String cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return "Booking cancelled successfully!";
    }

    // ✅ Admin: Fetch all bookings
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // ✅ Admin: Fetch recent 5 bookings
    @Override
    public List<Booking> getRecentBookings() {
        return bookingRepository.findAll().stream()
                .sorted(Comparator.comparing(Booking::getId).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // ✅ Get booking by ID (for details view)
    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));
    }
}





