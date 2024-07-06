package com.magnetic.domain.crew.dto.request.postdto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter

public class UpdatePostRequestDto {

    public String title;

    public String content;

    public String photoUrl;


    @Builder
    public UpdatePostRequestDto(String title, String content, String photoUrl) {
        this.title = title;
        this.content= content;
        this.photoUrl = photoUrl;
    }

}
