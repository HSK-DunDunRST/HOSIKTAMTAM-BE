package com.goormthonuniv.hoseo.hosiktamtam.controller.restaurant;

import com.goormthonuniv.hoseo.hosiktamtam.dto.restaurant.RestaurantAutocompleteResponse;
import com.goormthonuniv.hoseo.hosiktamtam.dto.restaurant.RestaurantDetailResponse;
import com.goormthonuniv.hoseo.hosiktamtam.dto.restaurant.RestaurantListResponse;
import com.goormthonuniv.hoseo.hosiktamtam.service.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<RestaurantListResponse> getRestaurants(
            @RequestParam("area") String area,
            @RequestParam(value = "sort", required = false) String sort) {
        return ResponseEntity.ok(restaurantService.getRestaurants(area, sort));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDetailResponse> getRestaurantDetail(
            @PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantDetail(restaurantId));
    }

    @GetMapping("/category")
    public ResponseEntity<RestaurantListResponse> getRestaurantsByCategory(
            @RequestParam("category") String category) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByCategory(category));
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<RestaurantAutocompleteResponse> autocompleteRestaurantName(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "limit", required = false) Integer limit) {
        return ResponseEntity.ok(restaurantService.autocompleteRestaurantName(keyword, limit));
    }
}