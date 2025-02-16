package com.cts.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
 
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for testing purposes
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/hithro/**").permitAll()
                .requestMatchers("api/hithro/admin/**").hasRole("ADMIN")
                .requestMatchers("api/hithro/director/**").hasRole("DIRECTOR")
                .requestMatchers("/api/hithro/doctor/**").hasRole("DOCTOR")
                .requestMatchers("api/hithro/reception/**").hasRole("RECEPTIONIST")
                .requestMatchers("api/hithro/patient/**").hasRole("PATIENT")
                .anyRequest().authenticated()
            )
            .httpBasic(); // Enable HTTP Basic authentication
 
        return http.build();
    }
 
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
 
        return new InMemoryUserDetailsManager(user);
    }
}