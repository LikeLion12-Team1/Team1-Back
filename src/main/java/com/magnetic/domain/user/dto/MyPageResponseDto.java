package com.magnetic.domain.user.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public record MyPageResponseDto() {
    @Builder
    public record MyPagePreview(
            MyCrewPreviewList myCrewPreviewList,
            MyChallengePreviewList myChallengePreviewList
    ) {
    }

    @Builder
    public record MyCrewPreviewList(
            List<MyCrewPreview> myCrewPreviewList
    ) {
    }

    @Builder
    public record MyChallengePreviewList(
            List<MyChallengePreview> myChallengePreviewList
    ) {
    }

    @Builder
    public record MyCrewPreview(
            String crewName,
            LocalDate startAt,
            LocalDate untilWhen
    ) {
    }

    @Builder
    public record MyChallengePreview(
            String challengeName,
            LocalDate startAt,
            LocalDate untilWhen,
            String status
    ) {
    }
}
