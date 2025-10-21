package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dash")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardViewController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/admin-dashboard")
    public String showDashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAllAttributes(adminDashboardService.getDashboardStats());
        return "admin-dashboard";
    }
}

