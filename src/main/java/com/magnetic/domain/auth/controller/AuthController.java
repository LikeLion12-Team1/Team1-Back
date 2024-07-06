package com.magnetic.domain.auth.controller;

import com.magnetic.domain.auth.dto.AuthRequestDto;
import com.magnetic.domain.auth.dto.AuthResponseDto;
import com.magnetic.domain.auth.dto.RegisterRequestDto;
import com.magnetic.domain.auth.service.AuthService;
import com.magnetic.domain.email.service.EmailService;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegisterRequestDto request) {
        authService.register(request);
        return ApiResponse.onSuccess("회원가입 성공");
    }

    @Operation(summary = "로그인", description = "로그인시 엑세스 토큰과 리프레시 토큰 발급")
    @PostMapping("/login")  // 로그인시 토큰 재 발급
    public ApiResponse<AuthResponseDto> authenticate(@RequestBody AuthRequestDto request) {
        return ApiResponse.onSuccess(authService.authenticate(request));
    }

    @Operation(summary = "리프레시 토큰 발급")
    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}