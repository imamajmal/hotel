package com.example.HotelReservationsystem.controller;

import com.example.HotelReservationsystem.entity.Booking;
import com.example.HotelReservationsystem.entity.Hotel;
import com.example.HotelReservationsystem.service.BookingService;
import com.example.HotelReservationsystem.service.HotelService;
import com.example.HotelReservationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingViewController {

    private final BookingService bookingService;
    private final HotelService hotelService;
    private final UserService userService;

    @GetMapping
    public String viewUserBookings(Model model, Authentication authentication) {
        String username = authentication.getName();
        Long userId = userService.getUserIdByUsername(username);

        List<Booking> bookings = bookingService.getUserBookings(userId);
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/book/{hotelId}")
    public String showBookingForm(@PathVariable Long hotelId, Authentication authentication, Model model) {
        String username = authentication.getName();
        Long userId = userService.getUserIdByUsername(username);
        Hotel hotel = hotelService.getHotelById(hotelId);

        model.addAttribute("hotel", hotel);
        model.addAttribute("userId", userId);
        return "book-hotel";
    }

    @PostMapping("/book/{hotelId}")
    public String createBooking(@PathVariable Long hotelId, @RequestParam Long userId, @ModelAttribute Booking booking) {
        bookingService.createBooking(userId, hotelId, booking);
        return "redirect:/bookings";
    }

 @GetMapping("/cancel/{bookingId}")
public String cancelBooking(@PathVariable Long bookingId,
                            RedirectAttributes redirectAttributes) {
    bookingService.cancelBooking(bookingId);
    redirectAttributes.addFlashAttribute("message", "Booking cancelled successfully!");
    return "redirect:/bookings";
}


    @GetMapping("/view/{bookingId}")
public String viewBookingDetails(@PathVariable Long bookingId, Model model) {
    Booking booking = bookingService.getBookingById(bookingId);
    model.addAttribute("booking", booking);
    return "booking-details"; // create booking-details.html if needed
}

}


