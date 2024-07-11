package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.postdto.CreatePostRequestDto;
import com.magnetic.domain.crew.dto.postdto.QueryPostResponse;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/crews/posts")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 목록 조회", description = "크루 게시글 전체 목록 조회")
    @GetMapping("/{crew_name}")
    public ApiResponse<QueryPostResponse.PostPreviewListDto> getPostList(
            @PathVariable("crew_name") String crewName
    ) {
        return ApiResponse.onSuccess(postService.getPostList(crewName));
    }

    //게시글 작성
    @Operation(summary = "게시글 생성: 내용, 카테고리 작성", description = "게시글 작성하기")
    @PostMapping("")
    public ApiResponse<Long> createPost(@RequestBody CreatePostRequestDto createPostRequestDto,
                                                   @AuthenticationPrincipal User user
    ) throws IOException {
        return ApiResponse.created(postService.createPost(createPostRequestDto, user));
    }

    //게시글 작성 사진 업로드
    @Operation(summary = "게시글 생성: 사진 업로드", description = "이미지 파일 업로드, 첨부 안해도 ok")
    @PostMapping(value = "/{post_id}",consumes = "multipart/form-data")
    public ApiResponse<QueryPostResponse.CreatedPostResponse> uploadPostImage(@RequestParam(value = "file", required = false) MultipartFile file,
                                                                          @PathVariable("post_id") Long postId,
                                                                          @AuthenticationPrincipal User user) {
        return ApiResponse.created(postService.uploadPostImage(postId, file, user));
    }

    //게시글 상세 조회
    @Operation(summary = "게시글 상세 조회", description = "postId로 게시글 상세 조회하기")
    @GetMapping("/detail/{postId}")
    public ApiResponse<PostResponseDto> getPost(@PathVariable Long postId,
                                                @AuthenticationPrincipal User user) {
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

    //게시글 신고
    @Operation(summary = "게시글 신고", description = "게시글 신고하기")
    @PostMapping("/{postId}/report")
    public ApiResponse<Boolean> reportPost(@PathVariable Long postId){
        boolean isReported = postService.reportPost(postId);
        return ApiResponse.onSuccess(isReported);
    }
}
