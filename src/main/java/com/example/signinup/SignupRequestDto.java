package com.example.signinup;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
public class SignupRequestDto {

    private String email;

    private String name;

    private String password;

    private LocalDateTime createdAt;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User.UserBuilder()
                .email(email)
                .name(name)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
