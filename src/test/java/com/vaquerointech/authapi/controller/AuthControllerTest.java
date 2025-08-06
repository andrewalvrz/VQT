package com.vaquerointech.authapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaquerointech.authapi.dto.RegisterRequest;
import com.vaquerointech.authapi.dto.RegisterResponseDTO;
import com.vaquerointech.authapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testRegisterReturnsUserId() throws Exception {
        // Prepare test data
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setName("Test User");
        request.setMajor("Computer Science");
        request.setGraduationDate(LocalDate.of(2023, 5, 15));
        request.setRole("STUDENT");

        Long expectedUserId = 123L;
        RegisterResponseDTO responseDTO = new RegisterResponseDTO(expectedUserId, "User registered successfully");
        
        // Mock service behavior
        when(userService.register(any(RegisterRequest.class))).thenReturn(responseDTO);
        
        // Perform request and validate
        MvcResult result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        
        // Verify response contains user ID
        RegisterResponseDTO actualResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(), 
                RegisterResponseDTO.class);
        
        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getUserId());
        assertEquals(expectedUserId, actualResponse.getUserId());
    }
}