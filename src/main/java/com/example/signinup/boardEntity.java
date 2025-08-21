package com.example.signinup;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Setter
@Getter
@Table(name="board")
@NoArgsConstructor
@AllArgsConstructor
public class boardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;


    @JoinColumn(name="User")
    private String email;

    private String title;

    private String content;

    private boolean isUse;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
