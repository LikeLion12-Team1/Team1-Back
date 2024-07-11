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
            String challengeName,
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

    @Builder
    public record ChallengingCrewPreviewList(
            List<ChallengingCrewPreviewDto> challengingCrewPreviewDtoList
    ) {}

    @Builder
    public record ChallengingCrewPreviewDto(
            Long crewId,
            String crewImgUrl,
            String crewName
    ) {}
}
