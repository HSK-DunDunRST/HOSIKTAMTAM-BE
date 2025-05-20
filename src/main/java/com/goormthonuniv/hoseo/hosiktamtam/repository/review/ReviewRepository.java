package com.goormthonuniv.hoseo.hosiktamtam.repository.review;

import com.goormthonuniv.hoseo.hosiktamtam.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRestaurantId(Long restaurantId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.restaurant.id = :restaurantId")
    Double getAverageRatingByRestaurantId(@Param("restaurantId") Long restaurantId);

    long countByRestaurantId(Long restaurantId);
}
