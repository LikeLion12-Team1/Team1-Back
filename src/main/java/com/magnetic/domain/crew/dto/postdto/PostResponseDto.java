package com.magnetic.domain.crew.dto.postdto;

import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private Long postId;

    private String category;

    private String nickname;

    private String content;

    private String photoUrl;

    //private String likeStatus;//Like랑 연결

    //public List<ReplyResponseDto> replies;

    public static PostResponseDto from(Post post, User user) {
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .category(post.getCategory())
                .nickname(user.getNickname())
                .content(post.getContent())
                .photoUrl(post.getPhotoUrl())
                .build();
    }


}
