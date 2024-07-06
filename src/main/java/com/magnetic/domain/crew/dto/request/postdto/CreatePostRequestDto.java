package com.magnetic.domain.crew.dto.request.postdto;

import com.magnetic.domain.crew.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter

public class CreatePostRequestDto {
    public String title;

    public String content;

    public String photo_url;


    @Builder
    public CreatePostRequestDto(String title, String content, String photo_url) {
        this.title = title;
        this.content= content;
        this.photo_url = photo_url;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .photo_url(photo_url)
                .build();
    }
}
