package com.magnetic.domain.crew.dto.postdto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record QueryPostResponse() {

    @Builder
    public record PostPreviewListDto(
            List<PostPreviewDto> postPreviewList
    ) {
    }

    @Builder
    public record PostPreviewDto (
            Long postId,
            String postImg,
            LocalDateTime createdAt,
            String author,
            String authorProfileImg,
            String content,
            String category,
            Long likeCount
    ) {}
}
