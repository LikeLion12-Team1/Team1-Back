package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.reportdto.ReportDto;
import com.magnetic.domain.crew.dto.reportdto.ReportRequestDto;
import com.magnetic.domain.crew.service.ReportService;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "게시글 신고", description = "게시글 신고하기")
    @PostMapping("/{postId}")
    public ApiResponse<ReportDto> reportPost(@PathVariable Long postId, @RequestBody ReportRequestDto reportRequestDto, @AuthenticationPrincipal User user){
        ReportDto reportDto = reportService.reportPost(postId, user, reportRequestDto);
        return ApiResponse.onSuccess(reportDto);

    }
}
