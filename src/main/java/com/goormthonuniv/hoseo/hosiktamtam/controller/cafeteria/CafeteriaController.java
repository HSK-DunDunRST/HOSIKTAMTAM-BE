package com.goormthonuniv.hoseo.hosiktamtam.controller.cafeteria;

import com.goormthonuniv.hoseo.hosiktamtam.dto.cafeteria.CafeteriaCongestionResponse;
import com.goormthonuniv.hoseo.hosiktamtam.service.cafeteria.CafeteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cafeteria")
@RequiredArgsConstructor
public class CafeteriaController {

    private final CafeteriaService cafeteriaService;

    @GetMapping("/congestion")
    public ResponseEntity<CafeteriaCongestionResponse> getCongestion() {
        return ResponseEntity.ok(cafeteriaService.getCongestion());
    }
}