package com.magnetic.domain.crew.dto.replydto;

import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.entity.Reply;
import com.magnetic.domain.user.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponseDto {

    private Long postId;

    private String nickname;

    private String content;

    public static ReplyResponseDto from(Post post, User user, Reply reply) {
        return ReplyResponseDto.builder()
                .postId(post.getPostId())
                .nickname(user.getNickname())
                .content(reply.getContent())
                .build();
    }
}