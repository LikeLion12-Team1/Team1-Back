package com.magnetic.domain.challenge.repository;

import com.magnetic.domain.challenge.dto.ChallengeResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepositoryCustom {

    List<ChallengeResponseDto.ChallengePreviewDto> findChallenges (
            String region, String sportsCategory);
}
