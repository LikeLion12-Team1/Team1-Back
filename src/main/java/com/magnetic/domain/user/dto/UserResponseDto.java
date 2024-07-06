package com.magnetic.domain.user.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public record UserResponseDto () {

    @Builder
    public record ProfilePreview (
            String nickname,
            String region,
            String profileImg,
            CrewPreviewList crewPreviewList
    ) {}

    @Builder
    public record CrewPreviewList (
            List<CrewPreview> crewPreviewList
    ) {}

    @Builder
    public record CrewPreview (
            String crewName
    ) {}
}
