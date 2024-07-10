package com.magnetic.domain.user.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record UserRequestDto() {

    public record Profile(
            String nickname,
            String region,
            String quitCrewName
    ) {}

    public record ProfileImg(
            MultipartFile profileImg
    ) {}

    public record UpdateUserPasswordDto(
            String email,
            String password
    ) {
    }
}
