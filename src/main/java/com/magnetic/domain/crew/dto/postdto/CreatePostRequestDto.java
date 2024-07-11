package com.magnetic.domain.crew.dto.postdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostRequestDto {
    private String category;
    private String content;
}
