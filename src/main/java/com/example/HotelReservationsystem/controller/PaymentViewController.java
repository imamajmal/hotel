package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.entity.Payment;
import com.example.HotelReservationsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentViewController {

    private final PaymentService paymentService;

    // 💳 Show Payment Page
    @GetMapping("/pay/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    public String showPaymentForm(@PathVariable Long bookingId, Model model) {
        model.addAttribute("bookingId", bookingId);
        return "payment"; // → payment.html
    }

    // ✅ Process Payment
    @PostMapping("/process/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    public String processPayment(@PathVariable Long bookingId,
                                 @RequestParam Double amount,
                                 Model model) {
        Payment payment = paymentService.makePayment(bookingId, amount);
        model.addAttribute("payment", payment);
        return "payment-success"; // → payment-success.html
    }

    // 🧾 View All Payments (Admin)
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public String viewAllPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payment-list"; // → payment-list.html
    }
}
