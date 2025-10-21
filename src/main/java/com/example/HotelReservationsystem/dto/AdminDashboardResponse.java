package com.example.HotelReservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Map;

@Data
@AllArgsConstructor
public class AdminDashboardResponse {
    private long totalUsers;
    private long totalHotels;
    private long totalBookings;
    private long totalPayments;
    private double totalRevenue;
    private Map<String, Long> bookingStatusCount;
    private Map<String, Long> paymentStatusCount;
}
