package com.magnetic.domain.plant.controller;

import com.magnetic.domain.plant.dto.PlantRequest;
import com.magnetic.domain.plant.dto.PlantResponse;
import com.magnetic.domain.plant.service.PlantService;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plants")
@CrossOrigin("*")
public class PlantController {

    private final PlantService plantService;

    @Operation(summary = "식물 목록 조회, 사용자 보유 토큰 수 조회",
            description = "사용자 보유 토큰 수 조회, 해금된 식물과 해금되지 않은 식물 확인, 해금 = 1, 해금 X = 0")
    @GetMapping("")
    public ApiResponse<PlantResponse.PlantPreviewListDto> getPlantList(
            @AuthenticationPrincipal User user
            )  {
        return ApiResponse.onSuccess(plantService.getUnlockedPlantList(user));
    }

    @Operation(summary = "사용자 대표 식물 설정",
            description = "해금된 식물 중 원하는 식물을 대표 식물로 설정, 대표 = 1, 대표 X = 0")
    @PostMapping("/{plant_id}")
    public ApiResponse<?> setMainPlant(
            @PathVariable(name = "plant_id") Long plantId,
            @RequestBody PlantRequest request,
            @AuthenticationPrincipal User user
    ) {
        return ApiResponse.onSuccess(plantService.setMainPlant(plantId, request.previousMainPlantId(), user));
    }

    @Operation(summary = "사용자 대표 식물 조회",
    description = "사용자가 설정한 대표 식물을 조회")
    @GetMapping("/main-plant")
    public ApiResponse<Long> getMainPlant(
            @AuthenticationPrincipal User user
    ) {
        return ApiResponse.onSuccess(plantService.getMainPlant(user));
    }

    @Operation(summary = "챌린지 달성 후 식물 해금",
            description = "챌린지 달성 여부 확인 후, 요청한 식물을 해금")
    @PostMapping("/unlock/{plant_id}")
    public ApiResponse<?> unlockPlant(
            @PathVariable(name = "plant_id") Long plantId,
            @AuthenticationPrincipal User user
    ) {
         return ApiResponse.onSuccess(plantService.unlockPlant(plantId, user));
    }

}
