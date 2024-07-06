package com.magnetic.domain.user.controller;

import com.magnetic.domain.user.dto.MyPageResponseDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.service.MyPageService;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/my")
public class MyPageController {

    private final MyPageService myPageService;

    @Operation(summary = "마이 페이지 조회", description = "소속 크루 목록, 소속 챌린지 목록 조회")
    @GetMapping("")
    public ApiResponse<MyPageResponseDto.MyPagePreview> getMyPage(
            @AuthenticationPrincipal User user
    ) {
        return ApiResponse.onSuccess(myPageService.getMyPage(user));
    }
}
