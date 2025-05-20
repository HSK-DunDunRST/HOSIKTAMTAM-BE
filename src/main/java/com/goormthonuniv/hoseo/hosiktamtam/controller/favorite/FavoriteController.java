package com.goormthonuniv.hoseo.hosiktamtam.controller.favorite;

import com.goormthonuniv.hoseo.hosiktamtam.dto.favorite.FavoriteRequest;
import com.goormthonuniv.hoseo.hosiktamtam.service.favorite.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<Void> addFavorite(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody FavoriteRequest request) {
        Long userId = Long.parseLong(userDetails.getUsername());
        favoriteService.addFavorite(userId, request.getRestaurantId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> removeFavorite(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long restaurantId) {
        Long userId = Long.parseLong(userDetails.getUsername());
        favoriteService.removeFavorite(userId, restaurantId);
        return ResponseEntity.ok().build();
    }
}
