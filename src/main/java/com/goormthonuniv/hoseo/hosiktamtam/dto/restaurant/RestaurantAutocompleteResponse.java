package com.goormthonuniv.hoseo.hosiktamtam.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantAutocompleteResponse {
    private List<AutocompleteResult> results;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AutocompleteResult {
        private Long restaurantId;
        private String name;
        private String mainImage;
    }
}