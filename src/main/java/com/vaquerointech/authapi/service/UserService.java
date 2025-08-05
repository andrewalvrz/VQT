package com.vaquerointech.authapi.service;

import com.vaquerointech.authapi.dto.LoginRequest;
import com.vaquerointech.authapi.dto.RegisterRequest;
import com.vaquerointech.authapi.entity.User;
import com.vaquerointech.authapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.userRepository = repo;
        this.passwordEncoder = encoder;
    }

    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return "Login successful";
    }
}
