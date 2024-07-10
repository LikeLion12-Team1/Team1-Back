package com.magnetic.domain.crew.dto.reportdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
//@AllArgsConstructor
public class ReportDto {

    private String reportReason;

    private Long postId;

    private Long userId;

    private LocalDate reportedAt;

    public ReportDto(String reportReason, Long postId, Long userId, LocalDate reportedAt) {
        this.reportReason = reportReason;
        this.postId = postId;
        this.userId = userId;
        this.reportedAt = reportedAt;
    }

}
