package com.example.HotelReservationsystem.repository;

import com.example.HotelReservationsystem.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByCityContainingIgnoreCase(String city);
}

