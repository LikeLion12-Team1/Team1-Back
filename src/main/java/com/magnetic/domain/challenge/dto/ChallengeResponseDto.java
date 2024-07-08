package com.magnetic.domain.challenge.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public record ChallengeResponseDto() {

    @Builder
    public record ChallengePreviewListDto(
            List<ChallengePreviewDto> challengePreviewDtoList
    ) {}

    @Builder
    public record ChallengePreviewDto(
            Long challengeId,
            LocalDate startAt,
            LocalDate untilWhen,
            String notice,
            Long memberCount
    ) {}

    @Builder
    public record ShareCrewPreviewListDto(
            List<ShareCrewPreviewDto> shareCrewPreviewDtoList
    ) {}

    @Builder
    public record ShareCrewPreviewDto(
            Long crewId,
            String crewName
    ) {}
}
