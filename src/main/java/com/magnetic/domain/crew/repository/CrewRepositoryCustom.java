package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.dto.crewdto.CrewPlantResponseDto;
import com.magnetic.domain.crew.dto.crewdto.CrewResponseDto;
import com.magnetic.domain.crew.dto.postdto.QueryPostResponse;
import com.magnetic.domain.crew.entity.Crew;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewRepositoryCustom {
    List<CrewResponseDto> findAllCrew(String region, String category);

    List<QueryPostResponse.PostPreviewDto> findAllPost(Crew crew);

    List<CrewPlantResponseDto> findAllUserAndMainPlantIdByCrew(Crew crew);
}
