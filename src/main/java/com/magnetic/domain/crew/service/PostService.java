package com.magnetic.domain.crew.service;

import com.magnetic.domain.crew.dto.request.postdto.CreatePostRequestDto;
import com.magnetic.domain.crew.dto.request.postdto.UpdatePostRequestDto;
import com.magnetic.domain.crew.dto.response.CrewResponseDto;
import com.magnetic.domain.crew.dto.response.PostResponseDto;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor//DI 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
@Transactional

public class PostService {

    private final PostRepository postRepository;

    //게시글 생성
    public PostResponseDto createPost(CreatePostRequestDto createPostRequestDto) {
        Post post = createPostRequestDto.toEntity();
        Post savedPost = postRepository.save(post);
        return PostResponseDto.from(savedPost);
    }

    //게시글 상세 조회
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return PostResponseDto.from(post);
    }

    //게시글 수정
    public PostResponseDto updatePost(Long postId, UpdatePostRequestDto updatePostRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.update(updatePostRequestDto);

        Post updatedPost = postRepository.save(post);
        return PostResponseDto.from(updatedPost);
    }

    //게시글 삭제
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        postRepository.delete(post);
    }
}
