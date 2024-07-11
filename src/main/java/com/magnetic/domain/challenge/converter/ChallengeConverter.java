package com.magnetic.domain.challenge.converter;

import com.magnetic.domain.challenge.dto.ChallengeResponseDto;
import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.crew.entity.Crew;

import java.util.List;

public class ChallengeConverter {

    public static ChallengeResponseDto.ChallengePreviewListDto toChallengePreviewList(List<Challenge> challengeList) {
        List<ChallengeResponseDto.ChallengePreviewDto> challengePreviewDtoList = challengeList.stream()
                .map(ChallengeConverter::toChallengePreview).toList();
        return ChallengeResponseDto.ChallengePreviewListDto.builder()
                .challengePreviewDtoList(challengePreviewDtoList)
                .build();
    }

    public static ChallengeResponseDto.ChallengePreviewDto toChallengePreview(Challenge challenge) {
        return ChallengeResponseDto.ChallengePreviewDto.builder()
                .challengeId(challenge.getChallengeId())
                .startAt(challenge.getStartAt())
                .untilWhen(challenge.getUntilWhen())
                .notice(challenge.getNotice())
                .build();

    }

    public static ChallengeResponseDto.ShareCrewPreviewListDto toCrewPreviewList(List<Crew> crewList) {
        List<ChallengeResponseDto.ShareCrewPreviewDto> crewPreviewDtoList = crewList.stream()
                .map(ChallengeConverter::toCrewPreview).toList();
        return ChallengeResponseDto.ShareCrewPreviewListDto.builder()
                .shareCrewPreviewDtoList(crewPreviewDtoList)
                .build();
    }

    public static ChallengeResponseDto.ShareCrewPreviewDto toCrewPreview(Crew crew) {
        return ChallengeResponseDto.ShareCrewPreviewDto.builder()
                .crewId(crew.getCrewId())
                .crewName(crew.getName())
                .build();
    }

    public static ChallengeResponseDto.ChallengingCrewPreviewDto toChallengingCrewPreviewDto(Crew crew) {
        return ChallengeResponseDto.ChallengingCrewPreviewDto.builder()
                .crewId(crew.getCrewId())
                .crewImgUrl(crew.getCrewImg())
                .crewName(crew.getName())
                .build();
    }

//    public static UserResponseDto.CrewPreview toCrewPreview(Crew crew) {
//        return UserResponseDto.CrewPreview.builder()
//                .crewName(crew.getName())
//                .build();
//    }
}
