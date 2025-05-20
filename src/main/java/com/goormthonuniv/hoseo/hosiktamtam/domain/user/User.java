package com.goormthonuniv.hoseo.hosiktamtam.domain.user;

import com.goormthonuniv.hoseo.hosiktamtam.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name = "oauth_id", nullable = false, unique = true)
    private String oauthId;

    public enum Provider {
        google, kakao
    }
}