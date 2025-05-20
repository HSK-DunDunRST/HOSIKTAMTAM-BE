package com.goormthonuniv.hoseo.hosiktamtam.domain.restaurant;

import com.goormthonuniv.hoseo.hosiktamtam.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "main_image")
    private String mainImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Area area;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> categories;

    public enum Area {
        campus, outside
    }
}
