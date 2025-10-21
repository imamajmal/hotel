package com.example.HotelReservationsystem.service.impl;

import com.example.HotelReservationsystem.entity.User;
import com.example.HotelReservationsystem.repository.UserRepository;
import com.example.HotelReservationsystem.service.JwtService;
import com.example.HotelReservationsystem.service.UserService;
import com.example.HotelReservationsystem.dto.LoginRequest;
import com.example.HotelReservationsystem.dto.LoginResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);
        return new LoginResponse(token, "Login successful!");
    }

      @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
 @Override
    public Long getUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    @Override
public List<User> getAllUsers() {
    return userRepository.findAll();
}

}

