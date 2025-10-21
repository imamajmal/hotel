package com.example.HotelReservationsystem.service;

import java.util.List;

import com.example.HotelReservationsystem.entity.Payment;

public interface PaymentService {
    Payment makePayment(Long bookingId, Double amount);
    Payment getPaymentByBooking(Long bookingId);
    List<Payment> getAllPayments();
  


   
    

}

