package com.example.HotelReservationsystem.service;

import java.util.Map;

import com.example.HotelReservationsystem.dto.AdminDashboardResponse;

public interface AdminDashboardService {
    AdminDashboardResponse getDashboardSummary();
    Map<String, Object> getDashboardStats();
}

