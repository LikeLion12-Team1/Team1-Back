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
@CrossOrigin("*")
public class MyPageController {

    private final MyPageService myPageService;

    @Operation(summary = "마이 페이지 조회", description = "일반 사용자 마이 페이지에서 소속 크루 목록, 소속 챌린지 목록 조회")
    @GetMapping("")
    public ApiResponse<MyPageResponseDto.MyPagePreview> getMyPage(
            @AuthenticationPrincipal User user
    ) {
        return ApiResponse.onSuccess(myPageService.getMyPage(user));
    }

    @Operation(summary = "마이 페이지 토큰 수령", description = "챌린지를 완료하고 보상을 수령")
    @PostMapping("/{challenge_id}")
    public ApiResponse<String> receiveToken(
            @AuthenticationPrincipal User user,
            @PathVariable("challenge_id") Long challengeId
    ) {
        return ApiResponse.onSuccess(myPageService.doneChallenge(user, challengeId));
    }

    @Operation(summary = "관리자: 마이크루 페이지 조회", description = "관리자 마이크루 페이지에서 해당 크루 멤버 목록, 커뮤니티 알림 목록 조회")
    @GetMapping("/crew/{crew_name}")
    public ApiResponse<MyPageResponseDto.AdminMyPagePreview> getAdminMyPage(
            @PathVariable(name = "crew_name") String crewName
    ) {
        return ApiResponse.onSuccess(myPageService.getAdminMyPage(crewName));
    }

    @Operation(summary = "관리자: 챌린지 인증", description = "관리자 마이크루 페이지에서 챌린지 게시글 인증 요청 승인")
    @PostMapping("/crew")
    public ApiResponse<MyPageResponseDto.VerifiedResult> verifyChallenge(
            @RequestParam("challenge-id") Long challengeId,
            @RequestParam("post-id") Long postId
    ) {
        return ApiResponse.onSuccess(myPageService.verifyChallengePost(challengeId, postId));
    }

    @Operation(summary = "관리자: 신고글 삭제", description = "관리자 마이크루 페이지에서 신고글 삭제")
    @DeleteMapping("/crew/delete/{post_id}")
    public ApiResponse<?> deletePost(
            @PathVariable(name = "post_id") Long postId
    ) {
        myPageService.deletePost(postId);
        return ApiResponse.onSuccess("해당 글이 삭제되었습니다.");
    }

    @Operation(summary = "관리자: 멤버 강퇴", description = "관리자 마이크루 페이지에서 크루 멤버 강퇴")
    @DeleteMapping("/crew/{crew_name}")
    public ApiResponse<?> kickMember(
            @PathVariable(name = "crew_name") String crewName,
            @RequestParam("user-id") Long kickMemberId
    ) {
        myPageService.kickMember(crewName, kickMemberId);
        return ApiResponse.onSuccess("멤버가 삭제되었습니다.");
    }
}
