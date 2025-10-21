package com.example.HotelReservationsystem.service.impl;

import com.example.HotelReservationsystem.entity.*;
import com.example.HotelReservationsystem.repository.BookingRepository;
import com.example.HotelReservationsystem.repository.PaymentRepository;
import com.example.HotelReservationsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Override
    public Payment makePayment(Long bookingId, Double amount) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found!"));

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount);

        // Simulate payment success or failure
        boolean paymentSuccess = new Random().nextBoolean();
        payment.setStatus(paymentSuccess ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentByBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found!"));

        return paymentRepository.findByBooking(booking)
                .orElseThrow(() -> new RuntimeException("Payment not found for this booking!"));
    }

    @Override
public List<Payment> getAllPayments() {
    return paymentRepository.findAll();
}




}
