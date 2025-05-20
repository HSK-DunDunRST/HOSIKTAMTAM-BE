package com.goormthonuniv.hoseo.hosiktamtam.repository.favorite;

import com.goormthonuniv.hoseo.hosiktamtam.domain.favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUserId(Long userId);

    Optional<Favorite> findByUserIdAndRestaurantId(Long userId, Long restaurantId);

    void deleteByUserIdAndRestaurantId(Long userId, Long restaurantId);

    long countByRestaurantId(Long restaurantId);
}
