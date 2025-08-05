package com.vaquerointech.authapi.controller;

import com.vaquerointech.authapi.dto.LoginRequest;
import com.vaquerointech.authapi.dto.RegisterRequest;
import com.vaquerointech.authapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to VaquerosInTech API!";
    }
}
