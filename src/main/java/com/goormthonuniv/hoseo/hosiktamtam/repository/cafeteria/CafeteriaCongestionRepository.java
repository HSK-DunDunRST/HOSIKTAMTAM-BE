package com.goormthonuniv.hoseo.hosiktamtam.repository.cafeteria;

import com.goormthonuniv.hoseo.hosiktamtam.domain.cafeteria.CafeteriaCongestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CafeteriaCongestionRepository extends JpaRepository<CafeteriaCongestion, Long> {

    @Query("SELECT c FROM CafeteriaCongestion c ORDER BY c.lastUpdated DESC")
    Optional<CafeteriaCongestion> findLatest();
}
