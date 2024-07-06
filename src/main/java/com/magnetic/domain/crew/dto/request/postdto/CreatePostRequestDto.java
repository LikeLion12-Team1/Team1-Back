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

    public String photoUrl;


    @Builder
    public CreatePostRequestDto(String title, String content, String photoUrl) {
        this.title = title;
        this.content= content;
        this.photoUrl = photoUrl;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .photoUrl(photoUrl)
                .build();
    }
}
