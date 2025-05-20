package com.goormthonuniv.hoseo.hosiktamtam.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private List<ReviewDto> reviews;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDto {
        private Long id;
        private Long userId;
        private String nickname;
        private String profileImage;
        private Integer rating;
        private String comment;
        private LocalDateTime createdAt;
    }
}