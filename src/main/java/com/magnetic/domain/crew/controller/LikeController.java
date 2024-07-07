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

    private final UserService userService;
    private final LikeService likeService;
    private final UserRepository userRepository;


    @PostMapping("push/{postId}")
    public ResponseEntity<ApiResponse<Void>> addLike(@PathVariable("postId")@Positive Long postId,
                                                     @AuthenticationPrincipal String email ) {
        //이메일을 불러옴
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 이메일을 가진 사용자가 존재하지 않습니다."));

        //id 랑 유저 추가
        likeService.addLike(postId, user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(null));
    }

}
