package com.goormthonuniv.hoseo.hosiktamtam.dto.recommendation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationResponse {
    private List<RecommendedMenuDto> recommendedMenus;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecommendedMenuDto {
        private String menuName;
        private String restaurantName;
        private Integer orderCount;
    }
}