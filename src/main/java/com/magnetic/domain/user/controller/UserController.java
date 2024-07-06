package com.magnetic.domain.user.controller;

import com.magnetic.domain.user.dto.UserRequestDto;
import com.magnetic.domain.user.dto.UserResponseDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.service.UserService;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "프로필 조회", description = "닉네임, 프로필 사진, 활동 지역, 소속 크루 조회")
    @GetMapping("/profile")
    public ApiResponse<UserResponseDto.ProfilePreview> getProfile(
            @AuthenticationPrincipal User user
            ) {
        return ApiResponse.onSuccess(userService.getProfile(user));
    }

//    @Operation(summary = "프로필 수정", description = "닉네임, 활동 지역, 소속 크루 탈퇴 여부를 수정")
//    @PostMapping("/profile")
//    public ApiResponse<UserResponseDto.ProfilePreview> updateProfile(
//            @RequestBody UserRequestDto.Profile request,
//            @AuthenticationPrincipal User user
//            ) {
//        return ApiResponse.onSuccess(userService.updateProfile(request, user));
//    }

//    @Operation(summary = "프로필 이미지 수정", description = "프로필 이미지를 수정")
//    @PostMapping("/profile-image")
//    public ApiResponse<UserResponseDto.ProfilePreview> updateProfileImg(
//            @RequestBody UserRequestDto.ProfileImg request,
//            @AuthenticationPrincipal User user
//    ) {
//        return ApiResponse.onSuccess(userService.updateProfileImg(request, user));
//    }

    @Operation(summary = "nickname 중복 확인")
    @GetMapping("/{nickname}")
    public ApiResponse<?> nicknameDuplicate(@PathVariable("nickname") String nickname) {
        if (userService.nicknameDuplicate(nickname)) {
            return ApiResponse.onSuccess("존재하는 닉네임 입니다.");
        } else {
            return ApiResponse.onFailure("해당 닉네임을 사용할 수 있습니다.");
        }
    }

    @Operation(summary = "비밀번호 재설정", description = "이메일 인증을 완료한 사용자가 비밀번호를 재설정")
    @PatchMapping("/password")
    public ApiResponse<?> setPassword(@RequestBody UserRequestDto.UpdateUserPasswordDto userPasswordDto) {
        userService.setPassword(userPasswordDto);
        return ApiResponse.onSuccess("비밀번호 재설정 완료");
    }
}
