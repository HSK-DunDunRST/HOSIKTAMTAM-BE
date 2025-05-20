package com.goormthonuniv.hoseo.hosiktamtam.service.menu;

import com.goormthonuniv.hoseo.hosiktamtam.domain.menu.Menu;
import com.goormthonuniv.hoseo.hosiktamtam.dto.menu.MenuSearchResponse;
import com.goormthonuniv.hoseo.hosiktamtam.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public MenuSearchResponse searchMenus(String query) {
        List<Menu> menus = menuRepository.findByNameContaining(query);

        List<MenuSearchResponse.MenuDto> menuDtos = menus.stream()
                .map(menu -> MenuSearchResponse.MenuDto.builder()
                        .menuId(menu.getId())
                        .menuName(menu.getName())
                        .restaurantName(menu.getRestaurant().getName())
                        .price(menu.getPrice())
                        .build())
                .collect(Collectors.<MenuSearchResponse.MenuDto>toList());

        return MenuSearchResponse.builder()
                .menus(menuDtos)
                .build();
    }
}