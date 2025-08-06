package com.vaquerointech.authapi.controller;

import com.vaquerointech.authapi.dto.RegisterRequest;
import com.vaquerointech.authapi.dto.RegisterResponseDTO;
import com.vaquerointech.authapi.dto.UserResponseDTO;
import com.vaquerointech.authapi.entity.User;
import com.vaquerointech.authapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/userinfo")
@CrossOrigin(origins = "*")
public class UserInfoController {

    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(UserResponseDTO.fromUserList(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserResponseDTO.fromUser(user));
    }

    @PostMapping
    public ResponseEntity<RegisterResponseDTO> createUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
}