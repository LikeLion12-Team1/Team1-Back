package com.magnetic.domain.crew.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LikeResponseDto {

    public Long userId;

    public Long postId;


}
