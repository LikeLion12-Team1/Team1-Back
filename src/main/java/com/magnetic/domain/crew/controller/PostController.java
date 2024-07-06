package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.request.postdto.CreatePostRequestDto;
import com.magnetic.domain.crew.dto.request.postdto.UpdatePostRequestDto;
import com.magnetic.domain.crew.dto.response.PostResponseDto;
import com.magnetic.domain.crew.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")

public class PostController {

    private final PostService postService;

    //게시글 생성
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody CreatePostRequestDto createPostRequestDto) {
        PostResponseDto postResponseDto = postService.createPost(createPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDto);
    }

    //게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.getPost(postId);
        return ResponseEntity.ok(postResponseDto);
    }

    //게시글  목록 조회
    @GetMapping("/posts/crew/{crewName}")
    public ResponseEntity<List<PostResponseDto>> getPostsByCrewName(@PathVariable String crewName) {
        List<PostResponseDto> posts = postService.getPostsByCrewName(crewName);
        return ResponseEntity.ok(posts);
    }


    //게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDto updatePostRequestDto) {
        PostResponseDto postResponseDto = postService.updatePost(postId, updatePostRequestDto);
        return ResponseEntity.ok(postResponseDto);
    }

    //게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
