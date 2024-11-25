package com.ssafy.ssafit.service;

import com.ssafy.ssafit.domain.JwtToken;
import com.ssafy.ssafit.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public JwtToken authenticateAndGenerateToken(String memberId, String password) {
        // 유저 인증 처리
        System.out.println(memberId + " " + password);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(memberId, password)
        );

        System.out.println(memberId + " " + password);

        // 인증에 성공하면 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }

    public JwtToken refreshAccessToken(String refreshToken) {
        if (jwtTokenProvider.validateToken(refreshToken)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
            return jwtTokenProvider.generateToken(authentication);
        }
        return null;
    }
}