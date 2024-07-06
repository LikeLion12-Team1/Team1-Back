package com.magnetic.domain.crew.service;

import com.magnetic.domain.crew.dto.request.crewdto.CreateCrewRequestDto;
import com.magnetic.domain.crew.dto.request.crewdto.UpdateCrewRequestDto;
import com.magnetic.domain.crew.dto.response.CrewResponseDto;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.repository.CrewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor//DI 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
@Transactional
public class CrewService {
    private final CrewRepository crewRepository;

    // 크루 생성
    public CrewResponseDto createCrew(CreateCrewRequestDto createCrewRequestDto) {
        Crew crew = createCrewRequestDto.toEntity();
        Crew savedCrew = crewRepository.save(crew);
        return CrewResponseDto.from(savedCrew);
    }

    // 크루 조회
    public CrewResponseDto getCrew(Long crewId) {
        Crew crew = crewRepository.findById(crewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 크루가 존재하지 않습니다."));
        return CrewResponseDto.from(crew);
    }

    // 크루 수정
    public CrewResponseDto updateCrew(Long crewId, UpdateCrewRequestDto updateCrewRequestDto) {
        Crew crew = crewRepository.findById(crewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 크루가 존재하지 않습니다."));

        crew.update(updateCrewRequestDto);

        Crew updatedCrew = crewRepository.save(crew);
        return CrewResponseDto.from(updatedCrew);
    }

    // 크루 삭제
    public void deleteCrew(Long crewId) {
        Crew crew = crewRepository.findById(crewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 크루가 존재하지 않습니다."));

        crewRepository.delete(crew);
    }




}

    
