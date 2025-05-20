package com.goormthonuniv.hoseo.hosiktamtam.service.favorite;

import com.goormthonuniv.hoseo.hosiktamtam.domain.favorite.Favorite;
import com.goormthonuniv.hoseo.hosiktamtam.domain.restaurant.Restaurant;
import com.goormthonuniv.hoseo.hosiktamtam.domain.user.User;
import com.goormthonuniv.hoseo.hosiktamtam.repository.favorite.FavoriteRepository;
import com.goormthonuniv.hoseo.hosiktamtam.repository.restaurant.RestaurantRepository;
import com.goormthonuniv.hoseo.hosiktamtam.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void addFavorite(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + restaurantId));

        // 이미 존재하는지 확인
        favoriteRepository.findByUserIdAndRestaurantId(userId, restaurantId)
                .ifPresent(favorite -> {
                    throw new IllegalStateException("Already favorite");
                });

        // 새로운 찜 추가
        Favorite favorite = Favorite.builder()
                .user(user)
                .restaurant(restaurant)
                .build();

        favoriteRepository.save(favorite);
    }

    @Transactional
    public void removeFavorite(Long userId, Long restaurantId) {
        // 존재하는지 확인
        favoriteRepository.findByUserIdAndRestaurantId(userId, restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Favorite not found"));

        // 찜 삭제
        favoriteRepository.deleteByUserIdAndRestaurantId(userId, restaurantId);
    }
}
