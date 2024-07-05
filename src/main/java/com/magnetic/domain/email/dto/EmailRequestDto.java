package com.magnetic.domain.email.dto;

public record EmailRequestDto(
) {
    public record CheckRequest (
            String email
    ) {}

    public record AuthRequest (
            String certificationNum
    ) {}
}
