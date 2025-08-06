package com.vaquerointech.authapi.client;

import com.vaquerointech.authapi.dto.UserResponseDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * This class demonstrates the response format of the UserInfo API.
 * It can be run as part of the application to show sample API responses.
 */
@Configuration
public class UserInfoApiClient {

    private final String BASE_URL = "http://localhost:8080/api/auth/userinfo";
    
    @Bean
    public CommandLineRunner demonstrateUserInfoApi() {
        return args -> {
            RestTemplate restTemplate = new RestTemplate();
            
            // Get all users
            System.out.println("=== GET /api/auth/userinfo ===");
            try {
                ResponseEntity<List<UserResponseDTO>> response = restTemplate.exchange(
                    BASE_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<UserResponseDTO>>() {}
                );
                
                List<UserResponseDTO> users = response.getBody();
                System.out.println("Response status: " + response.getStatusCode());
                System.out.println("Response body (List<UserResponseDTO>):");
                if (users != null && !users.isEmpty()) {
                    users.forEach(user -> {
                        System.out.println("  User {");
                        System.out.println("    id: " + user.getId() + ",");
                        System.out.println("    email: \"" + user.getEmail() + "\",");
                        System.out.println("    name: \"" + user.getName() + "\",");
                        System.out.println("    major: \"" + user.getMajor() + "\",");
                        System.out.println("    graduationDate: \"" + user.getGraduationDate() + "\",");
                        System.out.println("    role: \"" + user.getRole() + "\"");
                        System.out.println("  }");
                    });
                } else {
                    System.out.println("  [] (empty list - no users found)");
                }
            } catch (Exception e) {
                System.out.println("Error fetching all users: " + e.getMessage());
            }
            
            // Get user by ID (assuming ID 1 exists)
            System.out.println("\n=== GET /api/auth/userinfo/1 ===");
            try {
                ResponseEntity<UserResponseDTO> response = restTemplate.getForEntity(
                    BASE_URL + "/1", 
                    UserResponseDTO.class
                );
                
                UserResponseDTO user = response.getBody();
                System.out.println("Response status: " + response.getStatusCode());
                System.out.println("Response body (UserResponseDTO):");
                if (user != null) {
                    System.out.println("  User {");
                    System.out.println("    id: " + user.getId() + ",");
                    System.out.println("    email: \"" + user.getEmail() + "\",");
                    System.out.println("    name: \"" + user.getName() + "\",");
                    System.out.println("    major: \"" + user.getMajor() + "\",");
                    System.out.println("    graduationDate: \"" + user.getGraduationDate() + "\",");
                    System.out.println("    role: \"" + user.getRole() + "\"");
                    System.out.println("  }");
                } else {
                    System.out.println("  null (user not found)");
                }
            } catch (Exception e) {
                System.out.println("Error fetching user by ID: " + e.getMessage());
            }
            
            System.out.println("\nNote: The password field is no longer included in the response for security reasons.");
        };
    }
}