package com.example.HotelReservationsystem.repository;

import com.example.HotelReservationsystem.entity.Payment;
import com.example.HotelReservationsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBooking(Booking booking);
       List<Payment> findByStatus(String status);
}

