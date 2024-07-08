package com.magnetic.domain.crew.dto.postdto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter

public class UpdatePostRequestDto {

    public String postType;

    public String content;

    public String photoUrl;


    @Builder
    public UpdatePostRequestDto(String postType, String content, String photoUrl) {
        this.postType = postType;
        this.content= content;
        this.photoUrl = photoUrl;
    }

}
