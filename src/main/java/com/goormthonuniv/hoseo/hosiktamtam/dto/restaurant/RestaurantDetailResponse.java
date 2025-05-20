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
public class RestaurantDetailResponse {
    private Long id;
    private String name;
    private String mainImage;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String phoneNumber;
    private List<String> categories;
    private List<MenuDto> menus;
    private Double rating;
    private Long reviewCount;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MenuDto {
        private Long id;
        private String name;
        private Integer price;
        private String image;
    }
}