package com.example.signinup;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
public class SignupRequestDto {

    private String email;

    private String name;

    private String password;

    private boolean isUse;

    private LocalDateTime createdAt;

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        return new UserEntity.UserEntityBuilder()
                .email(email)
                .name(name)
                .isUse(isUse)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
