package com.goormthonuniv.hoseo.hosiktamtam.domain.review;

import com.goormthonuniv.hoseo.hosiktamtam.domain.common.BaseTimeEntity;
import com.goormthonuniv.hoseo.hosiktamtam.domain.restaurant.Restaurant;
import com.goormthonuniv.hoseo.hosiktamtam.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;
}