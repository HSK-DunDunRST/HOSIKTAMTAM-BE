package com.goormthonuniv.hoseo.hosiktamtam.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantListResponse {
    private List<RestaurantDto> restaurants;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RestaurantDto {
        private Long id;
        private String name;
        private List<String> category;
        private String mainImage;
        private LocalTime openTime;
        private LocalTime closeTime;
        private String phoneNumber;
        private List<String> availableDays;
        private Double rating;
        private Long favoriteCount;
    }
}