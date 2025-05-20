package com.goormthonuniv.hoseo.hosiktamtam.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private Long userId;
    private String nickname;
    private String profileImage;
    private List<FavoriteRestaurantDto> favorites;
    private List<RecentOrderDto> recentOrders;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FavoriteRestaurantDto {

        private Long restaurantId;
        private String name;
        private String mainImage;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecentOrderDto {

        private Long restaurantId;
        private String restaurantName;
        private String menuName;
        private Integer price;
    }
}
