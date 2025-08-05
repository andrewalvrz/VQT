package com.vaquerointech.authapi.dto;

import lombok.Data;
import java.time.LocalDate;


@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String major;
    private LocalDate graduationDate;
    private String role;
}
