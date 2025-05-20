package com.goormthonuniv.hoseo.hosiktamtam.service.user;

import com.goormthonuniv.hoseo.hosiktamtam.domain.favorite.Favorite;
import com.goormthonuniv.hoseo.hosiktamtam.domain.user.User;
import com.goormthonuniv.hoseo.hosiktamtam.dto.user.UserProfileResponse;
import com.goormthonuniv.hoseo.hosiktamtam.repository.favorite.FavoriteRepository;
import com.goormthonuniv.hoseo.hosiktamtam.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    @Transactional(readOnly = true)
    public UserProfileResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        List<Favorite> favorites = favoriteRepository.findByUserId(userId);

        List<UserProfileResponse.FavoriteRestaurantDto> favoriteRestaurants = favorites.stream()
                .map(favorite -> UserProfileResponse.FavoriteRestaurantDto.builder()
                        .restaurantId(favorite.getRestaurant().getId())
                        .name(favorite.getRestaurant().getName())
                        .mainImage(favorite.getRestaurant().getMainImage())
                        .build())
                .collect(Collectors.<UserProfileResponse.FavoriteRestaurantDto>toList());

        // 최근 주문은 실제 구현 시 별도의 Order 엔티티가 필요함
        // 여기서는 간단히 빈 리스트로 처리
        List<UserProfileResponse.RecentOrderDto> recentOrders = new ArrayList<>();

        return UserProfileResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .favorites(favoriteRestaurants)
                .recentOrders(recentOrders)
                .build();
    }
}