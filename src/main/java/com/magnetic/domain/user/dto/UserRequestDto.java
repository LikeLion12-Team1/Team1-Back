package com.magnetic.domain.user.dto;

import java.time.LocalDate;

public record UserRequestDto() {

    public record Profile(
            String nickname,
            String region,
            String quitCrewName
    ) {}

    public record ProfileImg(
            String profileImg
    ) {}

    public record UpdateUserPasswordDto(
            String email,
            String password
    ) {
    }
}
