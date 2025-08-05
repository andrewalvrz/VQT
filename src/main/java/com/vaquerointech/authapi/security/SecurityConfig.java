package com.vaquerointech.authapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.core.userdetails.UserDetails;


@Configuration
public class SecurityConfig {

















    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("andrew")
                .password(passwordEncoder().encode("andrewalvrz"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }






    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();


    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection (optional for APIs, but be aware of security implications)
                .csrf(csrf -> csrf.disable())
                // Require authentication for any request
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                // Enable default form login
                .formLogin(withDefaults());

        return http.build();
    }
}
