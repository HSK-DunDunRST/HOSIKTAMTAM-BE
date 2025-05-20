package com.goormthonuniv.hoseo.hosiktamtam.controller.auth;

import com.goormthonuniv.hoseo.hosiktamtam.dto.auth.OAuthLoginRequest;
import com.goormthonuniv.hoseo.hosiktamtam.dto.auth.TokenResponse;
import com.goormthonuniv.hoseo.hosiktamtam.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google")
    public ResponseEntity<TokenResponse> loginWithGoogle(@RequestBody OAuthLoginRequest request) {
        return ResponseEntity.ok(authService.loginWithGoogle(request));
    }

    @PostMapping("/kakao")
    public ResponseEntity<TokenResponse> loginWithKakao(@RequestBody OAuthLoginRequest request) {
        return ResponseEntity.ok(authService.loginWithKakao(request));
    }
}
