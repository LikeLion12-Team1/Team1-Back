package com.magnetic.domain.crew.dto.replydto;

import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.entity.Reply;
import com.magnetic.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponseDto {

    private String replyAuthorNickname;

    private String replyAuthorProfileImg;

    private String content;

    private LocalDateTime createdAt;
}
