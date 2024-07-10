package com.magnetic.domain.crew.dto.replydto;

import com.magnetic.domain.crew.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequestDto {

    private String content;

    public Reply toEntity() {
        return Reply.builder()
                .content(content)
                .build();
    }
}
