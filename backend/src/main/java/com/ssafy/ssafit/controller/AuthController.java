package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.JwtToken;
import com.ssafy.ssafit.dto.request.SignInRequestDTO;
import com.ssafy.ssafit.dto.response.JwtTokenDTO;
import com.ssafy.ssafit.security.JwtTokenProvider;
import com.ssafy.ssafit.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ssafy.ssafit.utils.CookieUtils.setRefreshTokenCookie;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthService authService, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 로그인 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> login(@RequestBody SignInRequestDTO loginRequest, HttpServletResponse response) {
        // 유저 인증 처리
        // 인증에 성공하면 토큰 생성
        JwtToken jwtToken = authService.authenticateAndGenerateToken(loginRequest.getMemberId(), loginRequest.getPassword());

        setRefreshTokenCookie(response, jwtToken.getRefreshToken());

        return ResponseEntity.ok(new JwtTokenDTO("Bearer", jwtToken.getAccessToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtTokenDTO> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = jwtTokenProvider.getRefreshTokenFromCookie(request);
        // Refresh Token을 검증하고 새로운 Access Token 발급
        if (jwtTokenProvider.validateToken(refreshToken)) {
            JwtToken newToken = authService.refreshAccessToken(refreshToken);

            setRefreshTokenCookie(response, newToken.getRefreshToken());

            return ResponseEntity.ok(new JwtTokenDTO("Bearer", newToken.getAccessToken()));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 유효하지 않은 Refresh Token
        }
    }
}