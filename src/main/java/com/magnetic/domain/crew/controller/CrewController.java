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

    // 크루 조회
    @GetMapping("/{crewId}")
    public ResponseEntity<CrewResponseDto> getCrew(@PathVariable Long crewId) {
        CrewResponseDto crewResponseDto = crewService.getCrew(crewId);
        return ResponseEntity.ok(crewResponseDto);
    }

    // 크루 수정
    @PatchMapping("/{crewId}")
    public ResponseEntity<CrewResponseDto> updateCrew(@PathVariable Long crewId, @RequestBody UpdateCrewRequestDto updateCrewRequestDto) {
        CrewResponseDto crewResponseDto = crewService.updateCrew(crewId, updateCrewRequestDto);
        return ResponseEntity.ok(crewResponseDto);
    }

    // 크루 삭제
    @DeleteMapping("/{crewId}")
    public ResponseEntity<Void> deleteCrew(@PathVariable Long crewId) {
        crewService.deleteCrew(crewId);
        return ResponseEntity.noContent().build();
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

