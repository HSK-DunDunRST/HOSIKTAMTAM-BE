package com.goormthonuniv.hoseo.hosiktamtam.service.review;

import com.goormthonuniv.hoseo.hosiktamtam.domain.restaurant.Restaurant;
import com.goormthonuniv.hoseo.hosiktamtam.domain.review.Review;
import com.goormthonuniv.hoseo.hosiktamtam.domain.user.User;
import com.goormthonuniv.hoseo.hosiktamtam.dto.review.ReviewRequest;
import com.goormthonuniv.hoseo.hosiktamtam.dto.review.ReviewResponse;
import com.goormthonuniv.hoseo.hosiktamtam.repository.restaurant.RestaurantRepository;
import com.goormthonuniv.hoseo.hosiktamtam.repository.review.ReviewRepository;
import com.goormthonuniv.hoseo.hosiktamtam.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void createReview(Long userId, ReviewRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + request.getRestaurantId()));

        // 평점 범위 검증 (1-5)
        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        // 리뷰 생성
        Review review = Review.builder()
                .user(user)
                .restaurant(restaurant)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public ReviewResponse getReviewsByRestaurantId(Long restaurantId) {
        // 식당 존재 여부 확인
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + restaurantId));

        // 리뷰 조회
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);

        List<ReviewResponse.ReviewDto> reviewDtos = reviews.stream()
                .map(review -> ReviewResponse.ReviewDto.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .nickname(review.getUser().getNickname())
                .profileImage(review.getUser().getProfileImage())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build())
                .collect(Collectors.toList());

        return ReviewResponse.builder()
                .reviews(reviewDtos)
                .build();
    }
}
