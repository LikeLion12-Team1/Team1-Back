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

    @Operation(summary = "MyCrew 관리자 페이지 조회", description = "해당 크루 멤버 목록, 커뮤니티 알림 목록 조회")
    @GetMapping("/crew")
    public ApiResponse<MyPageResponseDto.AdminMyPagePreview> getAdminMyPage(
            @AuthenticationPrincipal User user
    ) {
        return ApiResponse.onSuccess(myPageService.getAdminMyPage(user));
    }

    @Operation(summary = "관리자 챌린지 인증", description = "관리자 페이지에서 챌린지 요청 승인")
    @PostMapping("/crew/{challenge_id}")
    public ApiResponse<MyPageResponseDto.AdminMyPagePreview> verifyChallenge(
            @PathVariable(name = "challenge_id") Long challengeId,
            @AuthenticationPrincipal User user
    ) {
        return ApiResponse.onSuccess(myPageService.verifyChallenge(user, challengeId));
    }

    @Operation(summary = "관리자 멤버 강퇴", description = "관리자 페이지에서 크루 멤버 강퇴")
    @DeleteMapping("/crew/{user_id}")
    public ApiResponse<MyPageResponseDto.AdminMyPagePreview> kickMember(
            @PathVariable(name = "user_id") Long kickUserId,
            @AuthenticationPrincipal User user
    ) {
        return ApiResponse.onSuccess(myPageService.kickMember(user, kickUserId));
    }
}
