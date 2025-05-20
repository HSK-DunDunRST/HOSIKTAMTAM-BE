package com.goormthonuniv.hoseo.hosiktamtam.repository.menu;

import com.goormthonuniv.hoseo.hosiktamtam.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByRestaurantId(Long restaurantId);

    @Query("SELECT m FROM Menu m WHERE m.name LIKE %:query%")
    List<Menu> findByNameContaining(@Param("query") String query);
}