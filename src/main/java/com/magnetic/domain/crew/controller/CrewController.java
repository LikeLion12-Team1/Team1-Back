package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.request.crewdto.CreateCrewRequestDto;
import com.magnetic.domain.crew.dto.request.crewdto.UpdateCrewRequestDto;
import com.magnetic.domain.crew.dto.response.CrewResponseDto;
import com.magnetic.domain.crew.service.CrewService;
import com.magnetic.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/crews")

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
}

