package com.vaquerointech.authapi.service;

import com.vaquerointech.authapi.dto.LoginRequest;
import com.vaquerointech.authapi.dto.RegisterRequest;
import com.vaquerointech.authapi.dto.RegisterResponseDTO;
import com.vaquerointech.authapi.entity.User;
import com.vaquerointech.authapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Random random = new Random();

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.userRepository = repo;
        this.passwordEncoder = encoder;
    }
    
    /**
     * Generates a random 5-digit user ID that is not already in use
     * @return A unique 5-digit user ID
     */
    private String generateUniqueUserId() {
        String userId;
        do {
            // Generate a random number between 10000 and 99999 (5 digits)
            userId = String.format("%05d", 10000 + random.nextInt(90000));
        } while (userRepository.findByUserId(userId).isPresent());
        
        return userId;
    }

    public RegisterResponseDTO register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setMajor(request.getMajor());
        user.setGraduationDate(request.getGraduationDate());
        user.setRole(request.getRole());
        
        // Generate and assign a unique 5-digit user ID
        user.setUserId(generateUniqueUserId());

        user = userRepository.save(user);
        return RegisterResponseDTO.success(user.getId());
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return "Login successful";
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
    
    public String updateUser(Long id, RegisterRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        user.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        user.setName(request.getName());
        user.setMajor(request.getMajor());
        user.setGraduationDate(request.getGraduationDate());
        user.setRole(request.getRole());
        
        userRepository.save(user);
        return "User updated successfully";
    }
}
