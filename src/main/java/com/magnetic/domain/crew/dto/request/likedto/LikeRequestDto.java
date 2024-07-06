package com.magnetic.domain.crew.dto.request.likedto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter

public class LikeRequestDto {
    public Long user_id;

    public Long post_id;

    public LikeRequestDto(Long user_id, Long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }
}
