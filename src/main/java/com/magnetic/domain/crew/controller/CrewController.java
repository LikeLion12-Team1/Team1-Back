package com.magnetic.domain.crew.controller;

import com.magnetic.domain.crew.dto.crewdto.CreateCrewRequestDto;
import com.magnetic.domain.crew.dto.crewdto.UpdateCrewRequestDto;
import com.magnetic.domain.crew.dto.crewdto.CrewResponseDto;
import com.magnetic.domain.crew.service.CrewService;
import com.magnetic.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j//Lombok 라이브러리를 사용하여 로깅 기능을 제공
@RestController//REST API 컨트롤러임
@RequiredArgsConstructor//Lombok 라이브러리를 사용하여 생성자 주입 방식으로 의존성을 주입
@RequestMapping("/crews")//이 컨트롤러의 기본 URL 경로를 지정
public class CrewController {

    private final CrewService crewService;

    // 크루 생성
    @PostMapping
    //createCrew 메서드는 CreateCrewRequestDto 타입의 객체를 입력으로 받음
    // @RequestBody 어노테이션은 HTTP 요청 본문에서 데이터를 읽어와 createCrewRequestDto 객체에 매핑
    //ApiResponse<CrewResponseDto> 타입의 결과를 반환
    public ApiResponse<CrewResponseDto> createCrew(@RequestBody CreateCrewRequestDto createCrewRequestDto) {
        CrewResponseDto crewResponseDto = crewService.createCrew(createCrewRequestDto);
        return ApiResponse.onSuccess(crewResponseDto);
    }


    //크루 전체 조회
    @GetMapping//HTTP GET 요청을 처리하는 메서드
    //반환 타입은 ApiResponse<List<CrewResponseDto>>
    public ApiResponse<List<CrewResponseDto>> getAllCrews() {
        List<CrewResponseDto> crews = crewService.getAllCrews();//crewService라는 서비스 객체의 getAllCrews() 메서드를 호출하여 모든 Crew 데이터를 가져옴->그 결과를 crews 변수에 저장
        return ApiResponse.onSuccess(crews);//성공적인 API 응답을 생성->메서드는 crews 리스트를 API 응답 데이터로 포함
    }

    //크루 지역별 조회
    @GetMapping("/region")
    //region 파라미터를 받아서 해당 지역의 크루 정보를 조회
    //조회 결과는 CrewResponseDto 객체의 리스트로 반환
    public ApiResponse<List<CrewResponseDto>> getCrewsByRegion(@RequestParam String region) {
        List<CrewResponseDto> crews = crewService.getCrewsByRegion(region);//crewService에 getCrewsByRegion 메서드를 호출하여 해당 지역의 크루 정보를 조회
        return ApiResponse.onSuccess(crews);
    }

    //크루 스포츠 카테고리별 조회
    @GetMapping("/sports-category")
    public ApiResponse<List<CrewResponseDto>> getCrewsBySportsCategory(@RequestParam String sportsCategory) {
        List<CrewResponseDto> crews = crewService.getCrewsBySportsCategory(sportsCategory);
        return ApiResponse.onSuccess(crews);
    }

    //특정 크루 정보 조회
    @GetMapping("/{crewId}")
    public ApiResponse<CrewResponseDto> getCrew(@PathVariable Long crewId){
        CrewResponseDto crewResponseDto = crewService.getCrew(crewId);
        return ApiResponse.onSuccess(crewResponseDto);
    }


    // 크루 수정
    @PatchMapping("/{crewId}")
    //@PathVariable은 URL에서 {crewId} 변수의 값을 가져옴
    //@RequestBody는 요청 본문에서 UpdateCrewRequestDto 객체를 가져옴
    public ApiResponse<CrewResponseDto> updateCrew(@PathVariable Long crewId, @RequestBody UpdateCrewRequestDto updateCrewRequestDto) {
        CrewResponseDto crewResponseDto = crewService.updateCrew(crewId, updateCrewRequestDto);
        //crewService의 updateCrew 메서드를 호출하여 Crew 리소스를 업데이트
        //업데이트된 Crew 리소스는 CrewResponseDto 객체로 반환
        return ApiResponse.onSuccess(crewResponseDto);//업데이트된 Crew 리소스를 포함하는 ApiResponse 객체를 반환
    }

    // 크루 삭제
    @DeleteMapping("/{crewId}")
    //요청 URL에서 {crewId} 부분의 값을 받아 crewId 변수에 저장
    //ApiResponse<Void> 타입의 응답을 반환
    public ApiResponse<Void> deleteCrew(@PathVariable Long crewId) {
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
}

