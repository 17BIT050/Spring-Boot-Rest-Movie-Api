package com.training.capstone.MovieRestAPI.service;


import com.training.capstone.MovieRestAPI.model.User;
import com.training.capstone.MovieRestAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    private int adminCounter = 1;
    private int userCounter = 1;

    @PostConstruct
    public void init() {
        // Add a default user for testing
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("adminPass"));
        admin.setRole("ADMIN");
        admin.setUserId(generateUserId(admin.getRole()));
        admin.setEmail(admin.getUserId() + "gmail.com");
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("userPass"));
        user.setRole("USER");
        user.setUserId(generateUserId(user.getRole()));
        user.setEmail(user.getUserId() + "gmail.com");
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }


    private String generateUserId(String prefix) {
        if ("ADMIN".equals(prefix)) {
            return prefix + (adminCounter++);
        } else if ("USER".equals(prefix)) {
            return prefix + (userCounter++);
        }
        return prefix + "0";
    }
}
