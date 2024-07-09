package com.magnetic.domain.crew.dto.likedto;

import com.magnetic.domain.crew.dto.crewdto.CrewResponseDto;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.entity.Like;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.magnetic.domain.crew.entity.QPost.post;
import static com.magnetic.domain.user.entity.QUser.user;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LikeResponseDto {

    public Long userId;

    public Long postId;


    public static LikeResponseDto from(User user, Post post) {
        return LikeResponseDto.builder()
                .userId(user.getUserId())
                .postId(post.getPostId())
                .build();
    }


}
