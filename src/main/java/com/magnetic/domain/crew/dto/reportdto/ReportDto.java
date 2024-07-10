package com.magnetic.domain.crew.dto.reportdto;

import lombok.Builder;

import java.time.LocalDate;

public class ReportDto {

    private Long reportId;

    private String reportReason;

    private Long postId;

    private Long userId;

    private LocalDate reportedAt;

    @Builder
    public ReportDto(String reportReason, Long postId, Long userId, LocalDate reportedAt) {
        this.reportReason = reportReason;
        this.postId = postId;
        this.userId = userId;
        this.reportedAt = reportedAt;
    }

}
