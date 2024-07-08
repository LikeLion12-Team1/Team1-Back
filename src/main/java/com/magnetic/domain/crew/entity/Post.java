package com.magnetic.domain.crew.entity;

import com.magnetic.domain.crew.dto.postdto.UpdatePostRequestDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Entity
@Getter
@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.

public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column
    private String postType;
    @Column
    private String content;
    @Column
    private String photoUrl;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CrewPost> crewposts;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;

//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    @OrderBy("replyId asc")
//    private List<Reply> replies;



    public void update(UpdatePostRequestDto updatePostRequestDto) {
        postType = updatePostRequestDto.getPostType();
        content = updatePostRequestDto.getContent();
        photoUrl = updatePostRequestDto.getPhotoUrl();
    }


}
