package com.magnetic.domain.user.controller;

import com.magnetic.domain.user.dto.UserRequestDto;
import com.magnetic.domain.user.service.UserService;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "비밀번호 재설정", description = "이메일 인증을 완료한 사용자가 비밀번호를 재설정")
    @PatchMapping("/password")
    public ApiResponse<?> setPassword(@RequestBody UserRequestDto.UpdateUserPasswordDto userPasswordDto) {
        userService.setPassword(userPasswordDto);
        return ApiResponse.onSuccess("비밀번호 재설정 완료");
    }
}
