package com.magnetic.domain.crew.service;

import com.magnetic.domain.crew.dto.request.likedto.LikeRequestDto;
import com.magnetic.domain.crew.entity.Like;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.repository.LikeRepository;
import com.magnetic.domain.crew.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    //private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void insert(LikeRequestDto likeRequestDto) throws Exception {

        User user = userRepository.findById(likeRequestDto.getUser_id())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + heartRequestDTO.getMemberId()));

        Post post = postRepository.findById(likeRequestDto.getPost_id())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + heartRequestDTO.getBoardId()));

        // 이미 좋아요되어있으면 에러 반환
        if (likeRepository.findByUserAndBoard(user, post).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }

        Like like = Like.builder()
                .post(post)
                .user(user)
                .build();

        likeRepository.save(like);
    }

    @Transactional
    public void delete(LikeRequestDto likeRequestDto) {

        User user = userRepository.findById(likeRequestDto.getUser_id())
                .orElseThrow(() -> new NotFoundException("사용자가 존재하지 않습니다."));

        Post post = postRepository.findById(likeRequestDto.getPost_id())
                .orElseThrow(() -> new NotFoundException("게시글이 존재하지 않습니다."));

        Like like = likeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new NotFoundException("좋아요가 존재하지 않습니다."));

        likeRepository.delete(like);
    }

