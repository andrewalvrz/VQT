package com.vaquerointech.authapi.dto;

import com.vaquerointech.authapi.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object for User responses.
 * Excludes sensitive information like password hashes.
 */
@Data
public class UserResponseDTO {
    private Long id;
    private String userId;
    private String email;
    private String name;
    private String major;
    private LocalDate graduationDate;
    private String role;
    
    /**
     * Converts a User entity to UserResponseDTO
     * @param user The user entity to convert
     * @return A UserResponseDTO without sensitive information
     */
    public static UserResponseDTO fromUser(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUserId(user.getUserId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setMajor(user.getMajor());
        dto.setGraduationDate(user.getGraduationDate());
        dto.setRole(user.getRole());
        return dto;
    }
    
    /**
     * Converts a list of User entities to a list of UserResponseDTOs
     * @param users The list of user entities to convert
     * @return A list of UserResponseDTOs without sensitive information
     */
    public static List<UserResponseDTO> fromUserList(List<User> users) {
        return users.stream()
                .map(UserResponseDTO::fromUser)
                .collect(Collectors.toList());
    }
}