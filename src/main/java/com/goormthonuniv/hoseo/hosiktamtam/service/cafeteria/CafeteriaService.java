package com.goormthonuniv.hoseo.hosiktamtam.service.cafeteria;

import com.goormthonuniv.hoseo.hosiktamtam.domain.cafeteria.CafeteriaCongestion;
import com.goormthonuniv.hoseo.hosiktamtam.dto.cafeteria.CafeteriaCongestionResponse;
import com.goormthonuniv.hoseo.hosiktamtam.repository.cafeteria.CafeteriaCongestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CafeteriaService {

    private final CafeteriaCongestionRepository cafeteriaCongestionRepository;

    // 혼잡도 업데이트 주기 (분 단위)
    private static final int UPDATE_INTERVAL_MINUTES = 5;

    @Transactional(readOnly = true)
    public CafeteriaCongestionResponse getCongestion() {
        CafeteriaCongestion congestion = cafeteriaCongestionRepository.findLatest()
                .orElseThrow(() -> new IllegalStateException("No congestion data available"));

        return CafeteriaCongestionResponse.builder()
                .totalSeats(congestion.getTotalSeats())
                .usingSeats(congestion.getUsingSeats())
                .availableSeats(congestion.getAvailableSeats())
                .lastUpdated(congestion.getLastUpdated())
                .updateIntervalMinutes(UPDATE_INTERVAL_MINUTES)
                .build();
    }
}
