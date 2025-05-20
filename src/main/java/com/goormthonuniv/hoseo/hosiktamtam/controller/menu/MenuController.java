package com.goormthonuniv.hoseo.hosiktamtam.controller.menu;

import com.goormthonuniv.hoseo.hosiktamtam.dto.menu.MenuSearchResponse;
import com.goormthonuniv.hoseo.hosiktamtam.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/search")
    public ResponseEntity<MenuSearchResponse> searchMenus(
            @RequestParam("query") String query) {
        return ResponseEntity.ok(menuService.searchMenus(query));
    }
}
