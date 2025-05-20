package com.goormthonuniv.hoseo.hosiktamtam.service.auth;

import com.goormthonuniv.hoseo.hosiktamtam.domain.user.User;
import com.goormthonuniv.hoseo.hosiktamtam.dto.auth.OAuthLoginRequest;
import com.goormthonuniv.hoseo.hosiktamtam.dto.auth.TokenResponse;
import com.goormthonuniv.hoseo.hosiktamtam.repository.user.UserRepository;
import com.goormthonuniv.hoseo.hosiktamtam.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse loginWithGoogle(OAuthLoginRequest request) {
        // 실제 구현에서는 Google API 호출하여 토큰 검증 및 사용자 정보 가져오기
        // 여기서는 간단히 Mock 처리
        String oauthId = "google_" + Math.random(); // 실제로는 Google에서 받은 ID 사용
        String nickname = "구글사용자"; // 실제로는 Google에서 받은 이름 사용
        String profileImage = "https://example.com/default_profile.jpg"; // 실제로는 Google에서 받은 이미지 URL 사용

        User user = userRepository.findByOauthId(oauthId)
                .orElseGet(() -> userRepository.save(User.builder()
                        .oauthId(oauthId)
                        .provider(User.Provider.google)
                        .nickname(nickname)
                        .profileImage(profileImage)
                        .build()));

        return generateTokenResponse(user);
    }

    @Transactional
    public TokenResponse loginWithKakao(OAuthLoginRequest request) {
        // 실제 구현에서는 Kakao API 호출하여 토큰 검증 및 사용자 정보 가져오기
        // 여기서는 간단히 Mock 처리
        String oauthId = "kakao_" + Math.random(); // 실제로는 Kakao에서 받은 ID 사용
        String nickname = "카카오사용자"; // 실제로는 Kakao에서 받은 이름 사용
        String profileImage = "https://example.com/default_profile.jpg"; // 실제로는 Kakao에서 받은 이미지 URL 사용

        User user = userRepository.findByOauthId(oauthId)
                .orElseGet(() -> userRepository.save(User.builder()
                        .oauthId(oauthId)
                        .provider(User.Provider.kakao)
                        .nickname(nickname)
                        .profileImage(profileImage)
                        .build()));

        return generateTokenResponse(user);
    }

    private TokenResponse generateTokenResponse(User user) {
        String accessToken = jwtTokenProvider.generateAccessToken(user.getId().toString());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId().toString());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(TokenResponse.UserInfo.builder()
                        .userId(user.getId())
                        .nickname(user.getNickname())
                        .profileImage(user.getProfileImage())
                        .build())
                .build();
    }
}