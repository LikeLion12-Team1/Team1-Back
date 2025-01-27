package com.magnetic.domain.crew.service;

import com.magnetic.domain.crew.dto.likedto.LikeResponseDto;
import com.magnetic.domain.crew.entity.Like;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.repository.LikeRepository;
import com.magnetic.domain.crew.repository.PostRepository;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.exception.GeneralException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor//필수 인자를 가진 생성자를 자동으로 생성
@Transactional//이 클래스의 메서드들이 트랜잭션 처리됨

public class LikeService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;


    //addLike 메소드는 postId와 user 객체를 받아 게시글에 좋아요를 추가하거나 취소하는 기능을 수행
    public LikeResponseDto addLike(Long postId, User user) {
        Post post = postRepository.findById(postId)//postId를 사용하여 Post 엔티티를 조회
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));//만약 해당 게시글이 존재하지 않으면 예외를 던짐

        //현재 사용자가 해당 게시글에 좋아요를 누르지 않았다면
        if (!likeRepository.existsByUserAndPost(user, post)) {
            likeRepository.save(new Like(user, post));
            postRepository.updateLikeCount(post.getPostId(),1);
            return new LikeResponseDto(true,"좋아요 부여");
        } else {
            likeRepository.deleteByUserAndPost(user, post);
            postRepository.updateLikeCount(post.getPostId(),-1);
            return new LikeResponseDto(false,"좋아요 취소");
        }

    }
}





