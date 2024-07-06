package com.magnetic.domain.auth.dto;

public record AuthRequestDto(
        String email,
        String password
) {
}
