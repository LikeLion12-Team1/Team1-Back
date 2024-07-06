package com.magnetic.domain.challenge.converter;

import com.magnetic.domain.challenge.dto.ChallengeResponseDto;
import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.user.converter.UserConverter;
import com.magnetic.domain.user.dto.UserResponseDto;

import java.util.List;

public class ChallengeConverter {

    public static ChallengeResponseDto.ChallengePreviewListDto toChallengePreviewList(List<Challenge> challengeList) {
        List<ChallengeResponseDto.ChallengePreviewDto> challengePreviewDtoList = challengeList.stream()
                .map(ChallengeConverter::toChallengePreview).toList();
        return ChallengeResponseDto.ChallengePreviewListDto.builder()
                .challengePreviewDtoList(challengePreviewDtoList)
                .build();
    }

    private static ChallengeResponseDto.ChallengePreviewDto toChallengePreview(Challenge challenge) {
        return ChallengeResponseDto.ChallengePreviewDto.builder()
                .challengeId(challenge.getChallengeId())
                .startAt(challenge.getStartAt())
                .untilWhen(challenge.getUntilWhen())
                .notice(challenge.getNotice())
                .build();

    }

//    public static UserResponseDto.CrewPreview toCrewPreview(Crew crew) {
//        return UserResponseDto.CrewPreview.builder()
//                .crewName(crew.getName())
//                .build();
//    }
}
