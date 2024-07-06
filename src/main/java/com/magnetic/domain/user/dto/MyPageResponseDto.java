package com.magnetic.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MyPageResponseDto {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MyPagePreview {
        private List<MyCrewPreview> myCrewPreviewList;
        private List<MyChallengePreview> myChallengePreviewList;

    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MyCrewPreview {
        private Long crewId;
        private String crewName;
        private LocalDateTime createdAt;
        private LocalDate inactiveDate;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MyChallengePreview {
        private Long challengeId;
        private String challengeName;
        private LocalDate startAt;
        private LocalDate untilWhen;
        private String status;
    }
}
