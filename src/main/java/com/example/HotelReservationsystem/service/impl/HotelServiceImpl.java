package com.example.HotelReservationsystem.service.impl;

import com.example.HotelReservationsystem.entity.Hotel;
import com.example.HotelReservationsystem.repository.HotelRepository;
import com.example.HotelReservationsystem.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    // ✅ Get all hotels
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // ✅ Get hotel by ID
    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + id));
    }

    // ✅ Save new hotel
    @Override
    public void saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    // ✅ Update existing hotel
    @Override
    public void updateHotel(Long id, Hotel hotel) {
        Hotel existing = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + id));

        existing.setName(hotel.getName());
        existing.setCity(hotel.getCity());
        existing.setAddress(hotel.getAddress());
        existing.setDescription(hotel.getDescription());
        existing.setRating(hotel.getRating());

        hotelRepository.save(existing);
    }

    // ✅ Delete hotel
    @Override
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Hotel not found with ID: " + id);
        }
        hotelRepository.deleteById(id);
    }
}


