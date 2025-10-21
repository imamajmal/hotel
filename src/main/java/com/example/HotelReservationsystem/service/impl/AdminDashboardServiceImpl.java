package com.example.HotelReservationsystem.service.impl;

import com.example.HotelReservationsystem.dto.AdminDashboardResponse;
import com.example.HotelReservationsystem.entity.*;
import com.example.HotelReservationsystem.repository.*;
import com.example.HotelReservationsystem.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public AdminDashboardResponse getDashboardSummary() {

        long totalUsers = userRepository.count();
        long totalHotels = hotelRepository.count();
        long totalBookings = bookingRepository.count();
        long totalPayments = paymentRepository.count();

        // Total revenue (only successful payments)
        double totalRevenue = paymentRepository.findAll().stream()
                .filter(p -> p.getStatus() == PaymentStatus.SUCCESS)
                .mapToDouble(Payment::getAmount)
                .sum();

        // Booking status counts (CONFIRMED, CANCELLED)
        Map<String, Long> bookingStatusCount = bookingRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        b -> b.getStatus().name(), Collectors.counting()
                ));

        // Payment status counts (SUCCESS, FAILED)
        Map<String, Long> paymentStatusCount = paymentRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        p -> p.getStatus().name(), Collectors.counting()
                ));

        return new AdminDashboardResponse(
                totalUsers,
                totalHotels,
                totalBookings,
                totalPayments,
                totalRevenue,
                bookingStatusCount,
                paymentStatusCount
        );
    }

    @Override
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("hotelCount", hotelRepository.count());
        stats.put("userCount", userRepository.count());
        stats.put("bookingCount", bookingRepository.count());
        stats.put("paymentCount", paymentRepository.count());
        return stats;
    }


}
