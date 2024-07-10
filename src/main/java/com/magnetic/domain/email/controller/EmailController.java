package com.magnetic.domain.email.controller;

import com.magnetic.domain.email.dto.EmailRequestDto;
import com.magnetic.domain.email.service.EmailService;
import com.magnetic.domain.user.service.UserService;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;

    @Operation(summary = "인증번호 전송", description = "이메일을 입력하면 해당 주소로 인증번호 전송")
    @PostMapping("/mail/check")
    public ApiResponse<Object> emailConfirm(@RequestBody EmailRequestDto.CheckRequest emailCheckRequest) throws Exception {
        if (userService.emailExist(emailCheckRequest.email())) {
            String confirm = emailService.sendSimpleMessage(emailCheckRequest.email());
            if (confirm.isEmpty()) {
                return ApiResponse.onFailure("인증번호 전송 실패");
            } else {
                return ApiResponse.onSuccess("인증번호 전송 성공");
            }
        } else {
            return ApiResponse.onFailure("해당 이메일은 존재하지 않습니다.");
        }
    }

    @Operation(summary = "인증번호 확인", description = "이메일로 받은 인증번호 입력")
    @PostMapping("/mail/auth")
    public ApiResponse<?> emailAuthenticate(@RequestBody EmailRequestDto.AuthRequest emailAuthRequest) {
        if (emailAuthRequest.certificationNum().equals(EmailService.ePw)) {
            return ApiResponse.onSuccess(userService.redirectToken(emailAuthRequest));
        } else {
            return ApiResponse.onFailure("인증번호가 일치하지 않습니다.");
        }
    }
}
