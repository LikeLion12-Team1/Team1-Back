package com.magnetic.domain.challenge.service;

import com.magnetic.domain.challenge.converter.ChallengeConverter;
import com.magnetic.domain.challenge.dto.ChallengeResponseDto;
import com.magnetic.domain.challenge.repository.ChallengeRepository;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.repository.UserCrewRepository;
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

    public List<ChallengeResponseDto.ChallengePreviewDto> getChallengeList(String region, String sportsCategory) {
        return challengeRepository.findChallenges(region, sportsCategory);
    }

    public ChallengeResponseDto.ShareCrewPreviewListDto getCrewNameList(Long challengeId, User user) {
        List<Crew> crewList = userCrewRepository.findAllCrewByUserAndChallenge(user, challengeId);
        return ChallengeConverter.toCrewPreviewList(crewList);
    }
}
