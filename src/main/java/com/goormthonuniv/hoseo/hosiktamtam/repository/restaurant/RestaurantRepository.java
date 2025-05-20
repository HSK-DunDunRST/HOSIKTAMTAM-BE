package com.goormthonuniv.hoseo.hosiktamtam.repository.restaurant;

import com.goormthonuniv.hoseo.hosiktamtam.domain.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByArea(Restaurant.Area area);

    @Query("SELECT r FROM Restaurant r WHERE r.categories LIKE %:category%")
    List<Restaurant> findByCategory(@Param("category") String category);

    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE :keyword%")
    List<Restaurant> findByNameStartingWith(@Param("keyword") String keyword);

    @Query(value = "SELECT r.* FROM restaurant r ORDER BY (SELECT AVG(rv.rating) FROM review rv WHERE rv.restaurant_id = r.id) DESC", nativeQuery = true)
    List<Restaurant> findAllOrderByRatingDesc();

    @Query(value = "SELECT r.* FROM restaurant r ORDER BY (SELECT COUNT(*) FROM favorite f WHERE f.restaurant_id = r.id) DESC", nativeQuery = true)
    List<Restaurant> findAllOrderByFavoriteCountDesc();

    @Query(value = "SELECT r.* FROM restaurant r WHERE r.area = :area ORDER BY (SELECT AVG(rv.rating) FROM review rv WHERE rv.restaurant_id = r.id) DESC", nativeQuery = true)
    List<Restaurant> findByAreaOrderByRatingDesc(@Param("area") String area);

    @Query(value = "SELECT r.* FROM restaurant r WHERE r.area = :area ORDER BY (SELECT COUNT(*) FROM favorite f WHERE f.restaurant_id = r.id) DESC", nativeQuery = true)
    List<Restaurant> findByAreaOrderByFavoriteCountDesc(@Param("area") String area);
}
