package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.request.crewdto.CreateCrewRequestDto;
import com.magnetic.domain.crew.dto.request.crewdto.UpdateCrewRequestDto;
import com.magnetic.domain.crew.dto.response.CrewResponseDto;
import com.magnetic.domain.crew.service.CrewService;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/crews")
public class CrewController {

    private final CrewService crewService;

    // 크루 생성
    @PostMapping
    public ApiResponse<CrewResponseDto> createCrew(@RequestBody CreateCrewRequestDto createCrewRequestDto) {
        CrewResponseDto crewResponseDto = crewService.createCrew(createCrewRequestDto);
        return ApiResponse.onSuccess(crewResponseDto);
    }


    //크루 전체 조회
    @GetMapping
    public ApiResponse<List<CrewResponseDto>> getAllCrews() {
        List<CrewResponseDto> crews = crewService.getAllCrews();
        return ApiResponse.onSuccess(crews);
    }

    //크루 지역별 조회
    @GetMapping("/region")
    public ApiResponse<List<CrewResponseDto>> getCrewsByRegion(@RequestParam String region) {
        List<CrewResponseDto> crews = crewService.getCrewsByRegion(region);
        return ApiResponse.onSuccess(crews);
    }

    //크루 스포츠 카테고리별 조회
    @GetMapping("/sports-category")
    public ApiResponse<List<CrewResponseDto>> getCrewsBySportsCategory(@RequestParam String sportsCategory) {
        List<CrewResponseDto> crews = crewService.getCrewsBySportsCategory(sportsCategory);
        return ApiResponse.onSuccess(crews);
    }


    // 크루 수정
    @PatchMapping("/{crewId}")
    public ApiResponse<CrewResponseDto> updateCrew(@PathVariable Long crewId, @RequestBody UpdateCrewRequestDto updateCrewRequestDto) {
        CrewResponseDto crewResponseDto = crewService.updateCrew(crewId, updateCrewRequestDto);
        return ApiResponse.onSuccess(crewResponseDto);
    }

    // 크루 삭제
    @DeleteMapping("/{crewId}")
    public ApiResponse<Void> deleteCrew(@PathVariable Long crewId) {
        crewService.deleteCrew(crewId);
        return ApiResponse.noContent();
    }

    @Operation(summary = "크루 이름 중복 확인", description = "URI에 검사할 크루 이름 명시")
    @GetMapping("/check/{crew_name}")
    public ApiResponse<String> crewNameDuplicate(@PathVariable("crew_name") String crewName) {
        if (crewService.crewNameDuplicate(crewName)) {
            return ApiResponse.onSuccess("이미 존재하는 크루 이름입니다.");
        } else {
            return ApiResponse.onSuccess("해당 크루 이름을 사용할 수 있습니다.");
        }
    }
}

