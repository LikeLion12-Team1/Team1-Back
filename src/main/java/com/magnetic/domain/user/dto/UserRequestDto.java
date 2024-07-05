package com.magnetic.domain.user.dto;

public record UserRequestDto() {
    public record UpdateUserPasswordDto(
            String email,
            String password
    ) {
    }
}
