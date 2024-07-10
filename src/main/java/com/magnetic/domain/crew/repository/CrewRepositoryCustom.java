package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.dto.crewdto.CrewResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewRepositoryCustom {
    List<CrewResponseDto> findAllCrew(String region, String category);
}
