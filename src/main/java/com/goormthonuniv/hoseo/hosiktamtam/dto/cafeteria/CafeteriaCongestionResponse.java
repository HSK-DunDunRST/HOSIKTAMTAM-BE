package com.goormthonuniv.hoseo.hosiktamtam.dto.cafeteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeteriaCongestionResponse {
    private Integer totalSeats;
    private Integer usingSeats;
    private Integer availableSeats;
    private LocalDateTime lastUpdated;
    private Integer updateIntervalMinutes;
}