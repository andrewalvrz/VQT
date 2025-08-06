package com.vaquerointech.authapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for user registration that includes the user ID
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    private Long userId;
    private String message;
    
    public static RegisterResponseDTO success(Long userId) {
        return new RegisterResponseDTO(userId, "User registered successfully");
    }
}