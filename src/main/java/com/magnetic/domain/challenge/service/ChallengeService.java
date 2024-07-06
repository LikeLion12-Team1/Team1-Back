package com.magnetic.domain.challenge.service;

import com.magnetic.domain.challenge.converter.ChallengeConverter;
import com.magnetic.domain.challenge.dto.ChallengeResponseDto;
import com.magnetic.domain.challenge.repository.ChallengeRepository;
import com.magnetic.global.common.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public List<ChallengeResponseDto.ChallengePreviewDto> getChallengeList(String region, String sportsCategory) {
        return challengeRepository.findChallenges(region, sportsCategory);
    }
}
