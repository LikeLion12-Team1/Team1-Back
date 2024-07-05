package com.magnetic.domain.auth.dto;

import lombok.Builder;

@Builder
public record RegisterRequestDto(
        String email,
        String password,
        String region
) {
}
