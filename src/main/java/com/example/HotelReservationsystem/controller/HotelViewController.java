package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.entity.Hotel;
import com.example.HotelReservationsystem.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelViewController {

    private final HotelService hotelService;

    @GetMapping
    public String listHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "hotel-list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "add-hotel";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String saveHotel(@ModelAttribute Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editHotel(@PathVariable Long id, Model model) {
        model.addAttribute("hotel", hotelService.getHotelById(id));
        return "edit-hotel";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public String updateHotel(@PathVariable Long id, @ModelAttribute Hotel hotel) {
        hotelService.updateHotel(id, hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return "redirect:/hotels";
    }
}
