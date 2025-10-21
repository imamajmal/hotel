package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.entity.Hotel;
import com.example.HotelReservationsystem.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchViewController {

    private final SearchService searchService;

    // Show search form
    @GetMapping
    public String showSearchPage() {
        return "search";
    }

    // Process search and display results
    @GetMapping("/results")
    public String searchHotels(@RequestParam String city,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
                               Model model) {

        List<Hotel> availableHotels = searchService.searchAvailableHotels(city, checkInDate, checkOutDate);

        model.addAttribute("hotels", availableHotels);
        model.addAttribute("searchCity", city);
        model.addAttribute("checkInDate", checkInDate);
        model.addAttribute("checkOutDate", checkOutDate);

        return "search";
    }
}

