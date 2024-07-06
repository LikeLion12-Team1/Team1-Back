package com.magnetic.domain.crew.dto.response;

import com.magnetic.domain.crew.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PostResponseDto {

    public Long post_id;

    public String title;

    public String content;

    public String photo_url;

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .post_id(post.getPost_id())
                .title(post.getTitle())
                .content(post.getContent())
                .photo_url(post.getPhoto_url())
                .build();
    }
}
