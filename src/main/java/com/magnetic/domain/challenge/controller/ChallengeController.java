package com.magnetic.domain.challenge.controller;

import com.magnetic.domain.challenge.dto.ChallengeResponseDto;
import com.magnetic.domain.challenge.service.ChallengeService;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge")
@CrossOrigin("*")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Operation(summary = "챌린지 목록 조회", description = "현재 존재하는 챌린지 목록 조회")
    @GetMapping("")
    public ApiResponse<List<ChallengeResponseDto.ChallengePreviewDto>> getChallengeList(
            @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "sports-category", required = false) String sportsCategory) {
        return ApiResponse.onSuccess(challengeService.getChallengeList(region, sportsCategory));
    }

    //TODO 챌린지 특정 크루에게 공유 기능
    @Operation(summary = "내가 속한 크루 목록 조회", description = "해당 챌린지를 진행중이지 않은 크루 목록 조회")
    @GetMapping("/{challenge_id}")
    public ApiResponse<ChallengeResponseDto.ShareCrewPreviewListDto> getCrewList(
            @PathVariable(name = "challenge_id") Long challengeId,
            @AuthenticationPrincipal User user
            ) {
        return ApiResponse.onSuccess(challengeService.getCrewNameList(challengeId, user));
    }
}
