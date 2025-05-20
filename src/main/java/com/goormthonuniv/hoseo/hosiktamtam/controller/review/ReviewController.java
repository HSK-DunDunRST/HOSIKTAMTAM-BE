package com.goormthonuniv.hoseo.hosiktamtam.controller.review;

import com.goormthonuniv.hoseo.hosiktamtam.dto.review.ReviewRequest;
import com.goormthonuniv.hoseo.hosiktamtam.dto.review.ReviewResponse;
import com.goormthonuniv.hoseo.hosiktamtam.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> createReview(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ReviewRequest request) {
        Long userId = Long.parseLong(userDetails.getUsername());
        reviewService.createReview(userId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ReviewResponse> getReviews(
            @RequestParam("restaurant_id") Long restaurantId) {
        return ResponseEntity.ok(reviewService.getReviewsByRestaurantId(restaurantId));
    }
}
