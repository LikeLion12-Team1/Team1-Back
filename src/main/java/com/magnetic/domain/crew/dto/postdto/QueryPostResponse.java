package com.magnetic.domain.crew.dto.postdto;

import lombok.Builder;

import java.util.List;

public record QueryPostResponse() {

    @Builder
    public record PostPreviewListDto(
            List<PostPreviewDto> postPreviewList
    ) {
    }

    @Builder
    public record PostPreviewDto (
            String photoUrl,
            String crewName,
            String region,
            String category
    ) {}
}
