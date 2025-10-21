package com.example.HotelReservationsystem.service.impl;

import com.example.HotelReservationsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.HotelReservationsystem.entity.User appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // ✅ Ensure correct role prefix (Spring expects "ROLE_" internally)
        String roleName = appUser.getRole().name().startsWith("ROLE_")
                ? appUser.getRole().name().substring(5) // remove "ROLE_" if already there
                : appUser.getRole().name();

        return org.springframework.security.core.userdetails.User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(roleName) // ✅ ensures roles are properly recognized
                .build();
    }
}


