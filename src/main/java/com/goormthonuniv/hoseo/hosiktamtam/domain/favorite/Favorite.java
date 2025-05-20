package com.goormthonuniv.hoseo.hosiktamtam.domain.favorite;

import com.goormthonuniv.hoseo.hosiktamtam.domain.common.BaseTimeEntity;
import com.goormthonuniv.hoseo.hosiktamtam.domain.restaurant.Restaurant;
import com.goormthonuniv.hoseo.hosiktamtam.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite", uniqueConstraints = {
    @UniqueConstraint(name = "uniq_fav", columnNames = {"user_id", "restaurant_id"})
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
