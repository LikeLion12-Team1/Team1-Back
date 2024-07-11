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

@Entity
@Builder
@Getter
@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.

public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column
    private String content;
    @Column
    private String photoUrl;
    private String category;
    private Byte isVerified;
    private boolean isReported;
    private Long likeCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CrewPost> crewposts;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reply> replyList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Post(Long postId, String category, String content, String photoUrl, List<CrewPost> crewposts, List<Like> likes, List<Reply> replies) {
        this.postId = postId;
        this.category = category;
        this.content= content;
        this.photoUrl = photoUrl;
        this.crewposts = crewposts;
        this.likes = likes;
        this.replyList = replies;
    }

    public void update(UpdatePostRequestDto updatePostRequestDto) {
        category = updatePostRequestDto.getCategory();
        content = updatePostRequestDto.getContent();
        photoUrl = updatePostRequestDto.getPhotoUrl();
    }

    public void uploadImage(String url) {
        photoUrl = url;
    }

    public void verify() {
        if (isVerified == 0) {
            isVerified = 1;
        } else {
            isVerified = 0;
        }
    }

    public void reportPost() {
        this.isReported = true;
    }
}
