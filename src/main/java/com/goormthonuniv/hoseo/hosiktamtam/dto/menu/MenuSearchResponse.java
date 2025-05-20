package com.goormthonuniv.hoseo.hosiktamtam.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuSearchResponse {

    private List<MenuDto> menus;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MenuDto {

        private Long menuId;
        private String menuName;
        private String restaurantName;
        private Integer price;
    }
}
