package com.goormthonuniv.hoseo.hosiktamtam.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private Long restaurantId;
    private Integer rating;
    private String comment;
}