package com.magnetic.domain.user.controller;

import com.magnetic.domain.user.dto.UserRequestDto;
import com.magnetic.domain.user.dto.UserResponseDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.service.UserService;
import com.magnetic.global.common.ApiResponse;
import com.magnetic.s3.S3Manager;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final S3Manager fileService;

    @Operation(summary = "프로필 조회", description = "닉네임, 프로필 사진, 활동 지역, 소속 크루 조회")
    @GetMapping("/profile")
    public ApiResponse<UserResponseDto.ProfilePreview> getProfile(
            @AuthenticationPrincipal User user
            ) {
        return ApiResponse.onSuccess(userService.getProfile(user));
    }

    @Operation(summary = "프로필 수정", description = "닉네임, 활동 지역, 소속 크루 탈퇴 여부를 수정")
    @PostMapping("/profile")
    public ApiResponse<UserResponseDto.ProfilePreview> updateProfile(
            @RequestBody UserRequestDto.Profile request,
            @AuthenticationPrincipal User user
            ) {
        return ApiResponse.onSuccess(userService.updateProfile(request, user));
    }

    @Operation(summary = "프로필 이미지 수정", description = "프로필 이미지를 수정")
    @PostMapping(value = "/profile-image", consumes = "multipart/form-data")
    public ApiResponse<String> updateProfileImg(
            @RequestParam("file") MultipartFile multipartFile,
            @AuthenticationPrincipal User user
    ) throws IOException {
        userService.updateProfileImg(multipartFile, user);
        return ApiResponse.onSuccess("프로필 사진 업데이트 완료");
    }

    @Operation(summary = "소속 크루 탈퇴", description = "URI에 탈퇴할 크루명 명시")
    @PostMapping("/profile/{crew_name}")
    public ApiResponse<?> quitCrew(@PathVariable("crew_name") String crewName,
                                   @AuthenticationPrincipal User user) {
        userService.inactiveCrew(user, crewName);
        return ApiResponse.onSuccess("탈퇴 완료");
    }

    @Operation(summary = "email 중복 확인", description = "URI에 검사할 email 명시")
    @GetMapping("/check-email/{email}")
    public ApiResponse<String> emailDuplicate(@PathVariable("email") String email) {
        if (userService.emailExist(email)) {
            return ApiResponse.onSuccess("이미 존재하는 이메일입니다.");
        } else {
            return ApiResponse.onSuccess("해당 이메일을 사용할 수 있습니다.");
        }
    }

    @Operation(summary = "nickname 중복 확인", description = "URI에 검사할 nickname 명시")
    @GetMapping("/check/{nickname}")
    public ApiResponse<String> nicknameDuplicate(@PathVariable("nickname") String nickname) {
        if (userService.nicknameDuplicate(nickname)) {
            return ApiResponse.onSuccess("이미 존재하는 닉네임입니다.");
        } else {
            return ApiResponse.onSuccess("해당 닉네임을 사용할 수 있습니다.");
        }
    }

    @Operation(summary = "비밀번호 재설정", description = "이메일 인증을 완료한 사용자가 비밀번호를 재설정")
    @PatchMapping("/password")
    public ApiResponse<?> setPassword(@RequestBody UserRequestDto.UpdateUserPasswordDto userPasswordDto) {
        userService.setPassword(userPasswordDto);
        return ApiResponse.onSuccess("비밀번호 재설정 완료");
    }
}
