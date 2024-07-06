package com.magnetic.domain.challenge.controller;

import com.magnetic.domain.challenge.dto.ChallengeResponseDto;
import com.magnetic.domain.challenge.service.ChallengeService;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge")
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
}
