package com.goormthonuniv.hoseo.hosiktamtam.service.restaurant;

import com.goormthonuniv.hoseo.hosiktamtam.domain.menu.Menu;
import com.goormthonuniv.hoseo.hosiktamtam.domain.restaurant.Restaurant;
import com.goormthonuniv.hoseo.hosiktamtam.dto.restaurant.RestaurantAutocompleteResponse;
import com.goormthonuniv.hoseo.hosiktamtam.dto.restaurant.RestaurantDetailResponse;
import com.goormthonuniv.hoseo.hosiktamtam.dto.restaurant.RestaurantListResponse;
import com.goormthonuniv.hoseo.hosiktamtam.repository.favorite.FavoriteRepository;
import com.goormthonuniv.hoseo.hosiktamtam.repository.menu.MenuRepository;
import com.goormthonuniv.hoseo.hosiktamtam.repository.restaurant.RestaurantRepository;
import com.goormthonuniv.hoseo.hosiktamtam.repository.review.ReviewRepository;
import com.goormthonuniv.hoseo.hosiktamtam.util.KoreanSearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final FavoriteRepository favoriteRepository;
    private final ReviewRepository reviewRepository;
    private final KoreanSearchUtil koreanSearchUtil;

    @Transactional(readOnly = true)
    public RestaurantListResponse getRestaurants(String area, String sort) {
        Restaurant.Area areaEnum = Restaurant.Area.valueOf(area);
        List<Restaurant> restaurants;

        if (sort != null) {
            if (sort.equals("rating")) {
                restaurants = restaurantRepository.findByAreaOrderByRatingDesc(area);
            } else if (sort.equals("favorite")) {
                restaurants = restaurantRepository.findByAreaOrderByFavoriteCountDesc(area);
            } else {
                restaurants = restaurantRepository.findByArea(areaEnum);
            }
        } else {
            restaurants = restaurantRepository.findByArea(areaEnum);
        }

        List<RestaurantListResponse.RestaurantDto> restaurantDtos = restaurants.stream()
                .map(this::convertToRestaurantDto)
                .collect(Collectors.toList());

        return RestaurantListResponse.builder()
                .restaurants(restaurantDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public RestaurantDetailResponse getRestaurantDetail(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + restaurantId));

        List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);
        List<RestaurantDetailResponse.MenuDto> menuDtos = menus.stream()
                .map(menu -> RestaurantDetailResponse.MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .image(menu.getImage())
                .build())
                .collect(Collectors.toList());

        Double rating = reviewRepository.getAverageRatingByRestaurantId(restaurantId);
        Long reviewCount = reviewRepository.countByRestaurantId(restaurantId);

        return RestaurantDetailResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .mainImage(restaurant.getMainImage())
                .openTime(restaurant.getOpenTime())
                .closeTime(restaurant.getCloseTime())
                .phoneNumber(restaurant.getPhoneNumber())
                .categories(restaurant.getCategories())
                .menus(menuDtos)
                .rating(rating != null ? rating : 0.0)
                .reviewCount(reviewCount)
                .build();
    }

    @Transactional(readOnly = true)
    public RestaurantListResponse getRestaurantsByCategory(String category) {
        List<Restaurant> restaurants = restaurantRepository.findByCategory(category);

        List<RestaurantListResponse.RestaurantDto> restaurantDtos = restaurants.stream()
                .map(this::convertToRestaurantDto)
                .collect(Collectors.toList());

        return RestaurantListResponse.builder()
                .restaurants(restaurantDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public RestaurantAutocompleteResponse autocompleteRestaurantName(String keyword, Integer limit) {
        if (limit == null) {
            limit = 10;
        }

        List<Restaurant> restaurants;
        if (koreanSearchUtil.isChosungKeyword(keyword)) {
            // 초성 검색
            List<String> allRestaurantNames = restaurantRepository.findAll().stream()
                    .map(Restaurant::getName)
                    .collect(Collectors.toList());

            List<String> matchedNames = koreanSearchUtil.searchByChosung(allRestaurantNames, keyword);
            restaurants = restaurantRepository.findAll().stream()
                    .filter(r -> matchedNames.contains(r.getName()))
                    .limit(limit)
                    .collect(Collectors.toList());
        } else {
            // 일반 검색 (prefix matching)
            restaurants = restaurantRepository.findByNameStartingWith(keyword).stream()
                    .limit(limit)
                    .collect(Collectors.toList());
        }

        List<RestaurantAutocompleteResponse.AutocompleteResult> results = restaurants.stream()
                .map(restaurant -> RestaurantAutocompleteResponse.AutocompleteResult.builder()
                .restaurantId(restaurant.getId())
                .name(restaurant.getName())
                .mainImage(restaurant.getMainImage())
                .build())
                .collect(Collectors.toList());

        return RestaurantAutocompleteResponse.builder()
                .results(results)
                .build();
    }

    private RestaurantListResponse.RestaurantDto convertToRestaurantDto(Restaurant restaurant) {
        Double rating = reviewRepository.getAverageRatingByRestaurantId(restaurant.getId());
        Long favoriteCount = favoriteRepository.countByRestaurantId(restaurant.getId());

        // 예시로 월화수목금 모두 영업중이라고 가정
        List<String> availableDays = Arrays.asList("월", "화", "수", "목", "금");

        return RestaurantListResponse.RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .category(restaurant.getCategories())
                .mainImage(restaurant.getMainImage())
                .openTime(restaurant.getOpenTime())
                .closeTime(restaurant.getCloseTime())
                .phoneNumber(restaurant.getPhoneNumber())
                .availableDays(availableDays)
                .rating(rating != null ? rating : 0.0)
                .favoriteCount(favoriteCount)
                .build();
    }
}
