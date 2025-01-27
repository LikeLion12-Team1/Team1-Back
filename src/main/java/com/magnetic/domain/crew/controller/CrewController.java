package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.crewdto.*;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.service.CrewService;
import com.magnetic.domain.user.entity.User;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j//Lombok 라이브러리를 사용하여 로깅 기능을 제공
@RestController//REST API 컨트롤러임
@RequiredArgsConstructor//Lombok 라이브러리를 사용하여 생성자 주입 방식으로 의존성을 주입
@RequestMapping("api/v1/crews")//이 컨트롤러의 기본 URL 경로를 지정
@CrossOrigin("*")
public class CrewController {

    private final CrewService crewService;

    // 크루 생성: RequestBody 받기
    @Operation(summary = "크루 생성: RequestBody 받기", description = "크루 생성하기: 크루 정보 받기")
    @PostMapping()
    public ApiResponse<Long> createCrew(@RequestBody CreateCrewRequestDto createCrewRequestDto
    ) {
        return ApiResponse.onSuccess(crewService.createCrew(createCrewRequestDto));
    }

    // 크루 생성: 크루 사진 업로드
    @Operation(summary = "크루 생성: 이미지 업로드", description = "크루 생성하기: 크루 이미지 업로드")
    @PostMapping(value = "/{post_id}",consumes = "multipart/form-data")
    public ApiResponse<CrewResponseDto> createCrew(
            @PathVariable("post_id") Long postId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        CrewResponseDto crewResponseDto = crewService.createCrewUploadImg(postId, file);
        return ApiResponse.onSuccess(crewResponseDto);
    }

    //크루 전체 조회
    @Operation(summary = "크루 전체 조회", description = "모든 크루 조회")
    @GetMapping
    public ApiResponse<List<CrewResponseDto>> getAllCrews(
            @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "sports-category", required = false) String category
    ) {
        return ApiResponse.onSuccess(crewService.getAllCrewsByOptions(region, category));
    }

    //크루 지역별 조회
    @Operation(summary = "크루 지역별 조회", description = "지역별로 크루 조회하기")
    @GetMapping("/region")
    //region 파라미터를 받아서 해당 지역의 크루 정보를 조회
    //조회 결과는 CrewResponseDto 객체의 리스트로 반환
    public ApiResponse<List<CrewResponseDto>> getCrewsByRegion(@RequestParam String region) {
        List<CrewResponseDto> crews = crewService.getCrewsByRegion(region); // crewService에 getCrewsByRegion 메서드를 호출하여 해당 지역의 크루 정보를 조회
        return ApiResponse.onSuccess(crews);
    }

    //크루 스포츠 카테고리별 조회
    @Operation(summary = "크루 스포츠 카테고리별 조회", description = "스포츠 카테고리별로 크루 조회하기")
    @GetMapping("/sports-category")
    public ApiResponse<List<CrewResponseDto>> getCrewsBySportsCategory(@RequestParam String sportsCategory) {
        List<CrewResponseDto> crews = crewService.getCrewsBySportsCategory(sportsCategory);
        return ApiResponse.onSuccess(crews);
    }

    //특정 크루 정보 조회
    @Operation(summary = "특정 크루 조회", description = "크루 아이디로 특정 크루 상세정보 조회")
    @GetMapping("/{crew_id}")
    public ApiResponse<CrewCustomResponse.Preview> getCrew(@PathVariable("crew_id") Long crewId) {
        return ApiResponse.onSuccess(crewService.getCrew(crewId));
    }


    // 크루 수정
    @Operation(summary = "크루 수정", description = "크루 수정하기")
    @PatchMapping("/{crew_id}")
    //@PathVariable은 URL에서 {crewId} 변수의 값을 가져옴
    //@RequestBody는 요청 본문에서 UpdateCrewRequestDto 객체를 가져옴
    public ApiResponse<CrewResponseDto> updateCrew(@PathVariable("crew_id") Long crewId,
                                                   @RequestBody UpdateCrewRequestDto updateCrewRequestDto) {
        CrewResponseDto crewResponseDto = crewService.updateCrew(crewId, updateCrewRequestDto);
        //crewService의 updateCrew 메서드를 호출하여 Crew 리소스를 업데이트
        //업데이트된 Crew 리소스는 CrewResponseDto 객체로 반환
        return ApiResponse.onSuccess(crewResponseDto);//업데이트된 Crew 리소스를 포함하는 ApiResponse 객체를 반환
    }

    // 크루 삭제
    @Operation(summary = "크루 삭제", description = "크루 삭제하기")
    @DeleteMapping("/{crew_id}")
    //요청 URL에서 {crewId} 부분의 값을 받아 crewId 변수에 저장
    //ApiResponse<Void> 타입의 응답을 반환
    public ApiResponse<Void> deleteCrew(@PathVariable("crew_id") Long crewId) {
        crewService.deleteCrew(crewId);//crewService 객체의 deleteCrew() 메서드를 호출하여 해당 crewId의 크루를 삭제
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

    //크루 가입
    @Operation(summary = "크루 가입", description = "크루 가입하기")
    @PostMapping("/join/{crew_id}")
    public ApiResponse<JoinCrewDto> joinCrew(@PathVariable("crew_id") Long crewId, @AuthenticationPrincipal User user) {
        JoinCrewDto joinCrewDto = crewService.joinCrew(crewId, user);
        return ApiResponse.onSuccess(joinCrewDto);
    }

    //크루 플랜트 조회
    @Operation(summary = "크루원 식물 목록 조회 (크루 플랜트)", description = "크루원의 메인 식물 목록 조회하기")
    @GetMapping("/{crew_id}/crew-plant")
    public ApiResponse<List<CrewPlantResponseDto>> getCrewPlant(
            @PathVariable("crew_id") Long crewId){
        return ApiResponse.onSuccess(crewService.getCrewPlants(crewId));

    }

}

