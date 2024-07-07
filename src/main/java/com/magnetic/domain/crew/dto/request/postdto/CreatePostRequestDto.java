package com.magnetic.domain.crew.dto.request.postdto;

import com.magnetic.domain.crew.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter//getter 메서드를 자동으로 생성-> 이를 통해 필드 값을 읽을 수 있음

public class CreatePostRequestDto {
    public String title;

    public String content;

    public String photoUrl;


    //빌더 패턴을 사용하여 객체를 생성->필드 값을 유연하게 설정
    @Builder
    public CreatePostRequestDto(String title, String content, String photoUrl) {
        this.title = title;
        this.content= content;
        this.photoUrl = photoUrl;
    }

    //CreatePostRequestDto 객체를 Post 객체로 변환
    //Post 객체는 실제 데이터베이스에 저장될 Post 엔티티
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .photoUrl(photoUrl)
                .build();
    }
}
