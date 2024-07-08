package com.magnetic.domain.crew.entity;

import com.magnetic.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.


@Table(name = "post_like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    //likeId 생성자 필요없어서 @AllArgsConstructor 사용X
    public Like(User user, Post post){
        this.user = user;
        this.post =post;
    }


}



/* 채민오빠 참고 코드
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public static boolean isPresent() {
    }


}

 */
