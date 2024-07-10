package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.replydto.ReplyRequestDto;
import com.magnetic.domain.crew.dto.replydto.ReplyResponseDto;
import com.magnetic.domain.crew.service.ReplyService;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/")
public class ReplyController {

    private final ReplyService replyService;

    @Operation(summary = "댓글 작성", description = "게시글에 댓글 작성하기")
    @PostMapping("posts/{postId}/reply")
    public ApiResponse<ReplyResponseDto> createReply(@PathVariable Long postId, @RequestBody ReplyRequestDto replyRequestDto, @AuthenticationPrincipal User user){
        //서비스에 위임
        ReplyResponseDto replyResponseDto = replyService.createReply(postId, replyRequestDto, user);
        //결과응답
        return ApiResponse.created(replyResponseDto);
    }


    @Operation(summary = "게시글 댓글 목록 조회", description = "게시글 댓글 목록 조회하기")
    @GetMapping("posts/{postId}/reply")
    public ApiResponse<List<ReplyResponseDto>> getReplies(@PathVariable Long postId, @AuthenticationPrincipal User user){
        //서비스에 위임
        List<ReplyResponseDto> replyResponseDtos = replyService.getReplies(postId, user);

        return ApiResponse.onSuccess(replyResponseDtos);
    }

}
