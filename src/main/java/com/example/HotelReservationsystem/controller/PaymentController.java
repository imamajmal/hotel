package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.entity.Payment;
import com.example.HotelReservationsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // 👥 USER only: Make a payment for a booking
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/make/{bookingId}")
    public ResponseEntity<Payment> makePayment(
            @PathVariable Long bookingId,
            @RequestParam double amount
    ) {
        return ResponseEntity.ok(paymentService.makePayment(bookingId, amount));
    }

    // 👥 USER or ADMIN: Get payment details for a booking
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Payment> getPaymentByBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentByBooking(bookingId));
    }
}
