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
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;


    /**
     * UserEntity와의 다대일(N:1) 관계를 설정합니다.
     * 한 명의 유저(User)는 여러 개의 게시글(Board)을 작성할 수 있습니다.
     * FetchType.LAZY는 연관된 user 엔티티를 실제 사용할 때 조회하여 성능을 최적화합니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래 키로 user 테이블의 PK(id)를 사용합니다.
    private UserEntity user;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    private boolean isUse;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
