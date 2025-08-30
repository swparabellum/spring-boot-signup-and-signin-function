package com.example.signinup;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewPostDTO {

    private String title;

    private String content;

    private boolean isUse;

    private LocalDateTime createdAt;

}
