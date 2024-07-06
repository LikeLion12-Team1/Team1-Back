package com.magnetic.domain.crew.dto.request.likedto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter

public class LikeRequestDto {
    public Long userId;

    public Long postId;

    public LikeRequestDto(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
