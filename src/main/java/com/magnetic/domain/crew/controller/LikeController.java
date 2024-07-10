package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.likedto.LikeResponseDto;
import com.magnetic.domain.crew.service.LikeService;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.repository.UserRepository;
import com.magnetic.domain.user.service.UserService;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
@CrossOrigin("*")
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "좋아요 기능", description = "좋아요 부여 및 취소")
    @PostMapping("push/{postId}")
    public ApiResponse<LikeResponseDto> addLike(@PathVariable Long postId, @AuthenticationPrincipal User user) {
        //id 랑 유저 추가
        LikeResponseDto likeResponseDto = likeService.addLike(postId, user);
        return ApiResponse.onSuccess(likeResponseDto);
    }

}
