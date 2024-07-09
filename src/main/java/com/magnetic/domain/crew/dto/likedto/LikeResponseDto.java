package com.magnetic.domain.crew.dto.likedto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LikeResponseDto {

    private boolean isLiked;

    private String message;

}
