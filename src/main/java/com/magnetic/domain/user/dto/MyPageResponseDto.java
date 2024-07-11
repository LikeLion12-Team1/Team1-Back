package com.magnetic.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.DoubleStream;

public class MyPageResponseDto {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MyPagePreview {
        private List<MyCrewPreview> myCrewPreviewList;
        private List<MyChallengePreview> myChallengePreviewList;
        private Long userCount;
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
        private Long requiredVerificationCount;
        private Long verifiedCount;
    }

    @Builder
    public record AdminMyPagePreview(
            List<AdminMemberPreview> adminMemberPreviewList,
            List<AdminCommunityPreview> adminCommunityPreviewList
    ) {
    }

    @Builder
    public record AdminMemberPreviewList(
            List<AdminMemberPreview> adminMemberPreviewList
    ) {}

    @Builder
    public record AdminMemberPreview(
            Long userId,
            String nickname
    ) {
    }

    @Builder
    public record AdminCommunityPreviewList(
            List<AdminCommunityPreview> adminCommunityPreviewList
    ) {}

    @Builder
    public record AdminCommunityPreview(
            Long postId,
            Long authorId,
            LocalDateTime createdAt,
            String category
    ) {
    }

    @Builder
    public record VerifiedResult(
        Byte isVerified,
        Long verificationCount
    ) {}
}
