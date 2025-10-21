package com.example.HotelReservationsystem.repository;

import com.example.HotelReservationsystem.entity.Booking;
import com.example.HotelReservationsystem.entity.Hotel;
import com.example.HotelReservationsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    boolean existsByHotelAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            Hotel hotel, LocalDate checkOut, LocalDate checkIn);
    List<Booking> findTop5ByOrderByIdDesc();

   
   
}

