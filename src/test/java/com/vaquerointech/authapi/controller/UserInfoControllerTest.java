package com.vaquerointech.authapi.controller;

import com.vaquerointech.authapi.dto.RegisterRequest;
import com.vaquerointech.authapi.dto.RegisterResponseDTO;
import com.vaquerointech.authapi.entity.User;
import com.vaquerointech.authapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setUserId("12345");
        user1.setEmail("test1@example.com");
        
        User user2 = new User();
        user2.setId(2L);
        user2.setUserId("67890");
        user2.setEmail("test2@example.com");
        
        List<User> users = Arrays.asList(user1, user2);
        
        when(userService.getAllUsers()).thenReturn(users);
        
        mockMvc.perform(get("/api/auth/userinfo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUserId("12345");
        user.setEmail("test@example.com");
        
        when(userService.getUserById(anyLong())).thenReturn(user);
        
        mockMvc.perform(get("/api/auth/userinfo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testCreateUser() throws Exception {
        RegisterResponseDTO responseDTO = new RegisterResponseDTO(1L, "User registered successfully");
        when(userService.register(any(RegisterRequest.class))).thenReturn(responseDTO);
        
        String requestBody = "{\"email\":\"test@example.com\",\"password\":\"password\",\"name\":\"Test User\",\"major\":\"Computer Science\",\"graduationDate\":\"2023-05-15\",\"role\":\"STUDENT\"}";
        
        mockMvc.perform(post("/api/auth/userinfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testDeleteUser() throws Exception {
        when(userService.deleteUser(anyLong())).thenReturn("User deleted successfully");
        
        mockMvc.perform(delete("/api/auth/userinfo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testUpdateUser() throws Exception {
        when(userService.updateUser(anyLong(), any(RegisterRequest.class))).thenReturn("User updated successfully");
        
        String requestBody = "{\"email\":\"updated@example.com\",\"password\":\"newpassword\",\"name\":\"Updated User\",\"major\":\"Data Science\",\"graduationDate\":\"2024-05-15\",\"role\":\"STUDENT\"}";
        
        mockMvc.perform(post("/api/auth/userinfo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
}