package com.example.HotelReservationsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

   // private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                .requestMatchers("/api/hotels/add", "/api/hotels/update/**", "/api/hotels/delete/**").hasRole("ADMIN")
                .requestMatchers("/api/bookings/**").hasRole("USER")
                .requestMatchers("/api/payments/make/**").hasRole("USER")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/payments/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/search/**").permitAll()
                .requestMatchers("/api/hotels/**").permitAll()

               
               

                // 🌐 Public pages
                .requestMatchers("/", "/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()

                // 🏨 Everyone can view hotels
                .requestMatchers("/hotels", "/hotels/**").permitAll()

                // 📅 Bookings (User only)
                .requestMatchers("/bookings/**").hasAnyRole("USER", "ADMIN")

                // 🧑‍💼 Admin-only actions
                .requestMatchers("/admin/**", "/hotels/add", "/hotels/edit/**", "/hotels/delete/**").hasRole("ADMIN")

                // 🔐 Dashboard (any logged-in user)
                .requestMatchers("/dashboard", "/user-dashboard", "/admin-dashboard").authenticated()

                .requestMatchers("/admin-reports").hasRole("ADMIN")

                .requestMatchers("/search/**").permitAll()

                .requestMatchers("/payments/pay/**", "/payments/process/**").hasRole("USER")

                .requestMatchers("/payments/all").hasRole("ADMIN")

                .requestMatchers("/admin-dashboard", "/admin-reports").hasRole("ADMIN")



                
            
                
               

                .anyRequest().authenticated()
            )
            // ✅ Enable form login for Thymeleaf pages
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            // ⚠️ Disable JWT filter for now while testing frontend
            //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}



