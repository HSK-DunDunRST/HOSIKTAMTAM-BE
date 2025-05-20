package com.goormthonuniv.hoseo.hosiktamtam.domain.cafeteria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cafeteria_congestion")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeteriaCongestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "using_seats", nullable = false)
    private Integer usingSeats;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}