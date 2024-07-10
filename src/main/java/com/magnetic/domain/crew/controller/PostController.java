package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.postdto.CreatePostRequestDto;
import com.magnetic.domain.crew.dto.postdto.UpdatePostRequestDto;
import com.magnetic.domain.crew.dto.postdto.PostResponseDto;
import com.magnetic.domain.crew.service.PostService;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    //게시글 생성
    @Operation(summary = "게시글 생성", description = "게시글 생성하기")
    @PostMapping
    public ApiResponse<PostResponseDto> createPost(@RequestBody CreatePostRequestDto createPostRequestDto, @AuthenticationPrincipal User user) {
        PostResponseDto postResponseDto = postService.createPost(createPostRequestDto, user);
        return ApiResponse.created(postResponseDto);
    }



    //게시글 상세 조회
    @Operation(summary = "게시글 상세 조회", description = "postId로 게시글 상세 조회하기")
    @GetMapping("/{postId}")
    public ApiResponse<PostResponseDto> getPost(@PathVariable Long postId, @AuthenticationPrincipal User user) {
        PostResponseDto postResponseDto = postService.getPost(postId, user);
        return ApiResponse.onSuccess(postResponseDto);
    }


    //게시글 수정
    @Operation(summary = "게시글 수정", description = "게시글 수정하기")
    @PutMapping("/{postId}")
    public ApiResponse<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDto updatePostRequestDto, @AuthenticationPrincipal User user) {
        PostResponseDto postResponseDto = postService.updatePost(postId, updatePostRequestDto, user);
        return ApiResponse.onSuccess(postResponseDto);
    }

    //게시글 삭제
    @Operation(summary = "게시글 삭제", description = "게시글 삭제하기")
    @DeleteMapping("/{postId}")
    public ApiResponse<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ApiResponse.noContent();
    }
}
