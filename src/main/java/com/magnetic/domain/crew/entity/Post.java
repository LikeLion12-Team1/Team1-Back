package com.magnetic.domain.crew.entity;

import com.magnetic.domain.crew.dto.request.crewdto.UpdateCrewRequestDto;
import com.magnetic.domain.crew.dto.request.postdto.UpdatePostRequestDto;
import com.magnetic.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
//@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.

public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String photo_url;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CrewPost> crewposts;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reply> replies;

    @Builder
    public Post(Long post_id, String title, String content, String photo_url, List<CrewPost> crewposts, List<Like> likes, List<Reply> replies) {
        this.post_id = post_id;
        this.title = title;
        this.content= content;
        this.photo_url = photo_url;
        this.crewposts = crewposts;
        this.likes = likes;
        this.replies = replies;
    }

    public void update(UpdatePostRequestDto updatePostRequestDto) {
        title = updatePostRequestDto.getTitle();
        content = updatePostRequestDto.getContent();
        photo_url = updatePostRequestDto.getPhoto_url();
    }


}
