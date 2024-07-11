package com.magnetic.domain.challenge.service;

import com.magnetic.domain.challenge.converter.ChallengeConverter;
import com.magnetic.domain.challenge.dto.ChallengeResponseDto;
import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.challenge.repository.ChallengeRepository;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.repository.CrewChallengeRepository;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.repository.UserCrewRepository;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.ChallengeHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserCrewRepository userCrewRepository;
    private final CrewChallengeRepository crewChallengeRepository;

    public List<ChallengeResponseDto.ChallengePreviewDto> getChallengeList(String region, String sportsCategory) {
        return challengeRepository.findChallenges(region, sportsCategory);
    }

    public ChallengeResponseDto.ShareCrewPreviewListDto getCrewNameList(Long challengeId, User user) {
        List<Crew> crewList = userCrewRepository.findAllCrewByUserAndChallenge(user, challengeId);
        return ChallengeConverter.toCrewPreviewList(crewList);
    }

    public ChallengeResponseDto.ChallengingCrewPreviewList getCrewListOnChallenging(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new ChallengeHandler(ErrorStatus._NOT_FOUND_CHALLENGE));

        List<Crew> crewList = crewChallengeRepository.findAllCrewByChallenge(challenge);
        List<ChallengeResponseDto.ChallengingCrewPreviewDto> challengingCrewPreviewDtoList = crewList.stream()
                .map(ChallengeConverter::toChallengingCrewPreviewDto).toList();

        return ChallengeResponseDto.ChallengingCrewPreviewList.builder()
                .challengingCrewPreviewDtoList(challengingCrewPreviewDtoList)
                .build();
    }
}
