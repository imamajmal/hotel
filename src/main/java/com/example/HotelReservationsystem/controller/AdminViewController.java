package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.service.BookingService;
import com.example.HotelReservationsystem.service.HotelService;
import com.example.HotelReservationsystem.service.PaymentService;
import com.example.HotelReservationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor // ✅ This automatically generates a constructor for all final fields
public class AdminViewController {

    private final HotelService hotelService;
    private final UserService userService;
    private final BookingService bookingService;
    private final PaymentService paymentService;

    @GetMapping("/admin-dashboard")
    public String adminDashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().iterator().next().getAuthority());
        return "admin-dashboard";
    }

    // ✅ Admin Reports Page
    @GetMapping("/admin-reports")
    public String adminReports(Model model) {
        model.addAttribute("hotelCount", hotelService.getAllHotels().size());
        model.addAttribute("userCount", userService.getAllUsers().size());
        model.addAttribute("bookingCount", bookingService.getAllBookings().size());
        model.addAttribute("paymentCount", paymentService.getAllPayments().size());
        model.addAttribute("recentBookings", bookingService.getRecentBookings());

        return "admin-reports";
    }
}


