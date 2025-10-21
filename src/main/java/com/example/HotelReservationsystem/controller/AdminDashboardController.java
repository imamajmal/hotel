package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.dto.AdminDashboardResponse;
import com.example.HotelReservationsystem.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    // 🔒 Only ADMIN can access this
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/summary")
    public ResponseEntity<AdminDashboardResponse> getDashboardSummary() {
        return ResponseEntity.ok(adminDashboardService.getDashboardSummary());
    }
}
