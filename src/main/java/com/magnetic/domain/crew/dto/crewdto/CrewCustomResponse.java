package com.magnetic.domain.crew.dto.crewdto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record CrewCustomResponse() {

    @Builder
    public record Preview(
            String crewImg,
            String crewName,
            Long memberCount,
            List<CrewChallengePreview> crewChallengePreviewList,
            LocalDate crewStartAt
    ) {
    }

    @Builder
    public record CrewChallengePreviewList(
            List<CrewChallengePreview> crewChallengePreviewList
    ) {
    }

    @Builder
    public record CrewChallengePreview(
            LocalDate startAt,
            LocalDate untilWhen,
            String challengeName
    ) {
    }
}
