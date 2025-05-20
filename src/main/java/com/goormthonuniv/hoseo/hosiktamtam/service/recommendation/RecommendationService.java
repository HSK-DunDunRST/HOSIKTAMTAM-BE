package com.goormthonuniv.hoseo.hosiktamtam.service.recommendation;

import com.goormthonuniv.hoseo.hosiktamtam.dto.recommendation.RecommendationResponse;
import com.goormthonuniv.hoseo.hosiktamtam.repository.favorite.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final FavoriteRepository favoriteRepository;

    @Transactional(readOnly = true)
    public RecommendationResponse getRecommendations(Long userId) {
        // 실제 구현에서는 사용자의 찜 목록, 리뷰 데이터, 주문 내역 등을 기반으로 추천 알고리즘 적용
        // 여기서는 간단히 Mock 데이터 반환

        List<RecommendationResponse.RecommendedMenuDto> recommendedMenus = new ArrayList<>();

        // 예시 데이터
        recommendedMenus.add(RecommendationResponse.RecommendedMenuDto.builder()
                .menuName("탕수육 + 짜장면")
                .restaurantName("진보")
                .orderCount(5)
                .build());

        recommendedMenus.add(RecommendationResponse.RecommendedMenuDto.builder()
                .menuName("마라탕")
                .restaurantName("한우사골마라탕")
                .orderCount(3)
                .build());

        recommendedMenus.add(RecommendationResponse.RecommendedMenuDto.builder()
                .menuName("돈까스")
                .restaurantName("가츠시")
                .orderCount(2)
                .build());

        return RecommendationResponse.builder()
                .recommendedMenus(recommendedMenus)
                .build();
    }
}
