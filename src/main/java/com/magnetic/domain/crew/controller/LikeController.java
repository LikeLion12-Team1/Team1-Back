package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.service.LikeService;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.repository.UserRepository;
import com.magnetic.domain.user.service.UserService;
import com.magnetic.global.common.ApiResponse;
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

public class LikeController {

    private final LikeService likeService;

    @PostMapping("push/{postId}")
    public ApiResponse<Void> addLike(@PathVariable Long postId, @AuthenticationPrincipal User user) {
        //id 랑 유저 추가
        likeService.addLike(postId, user);
        return ApiResponse.onSuccess(null);
    }

}
