package com.example.HotelReservationsystem.service;

import com.example.HotelReservationsystem.entity.User;

import java.util.List;

import com.example.HotelReservationsystem.dto.LoginRequest;
import com.example.HotelReservationsystem.dto.LoginResponse;

public interface UserService {
    String registerUser(User user);
    LoginResponse loginUser(LoginRequest request);
   Long getUserIdByUsername(String username);
    User getUserByUsername(String username);
    User findByUsername(String username);
    List<User> getAllUsers();

}

