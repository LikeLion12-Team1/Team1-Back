package com.magnetic.domain.crew.converter;

import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.crew.dto.crewdto.CrewCustomResponse;

import java.util.List;

public class CrewConverter {

    public static CrewCustomResponse.CrewChallengePreviewList toChallengePreviewList(List<Challenge> challengeList) {
        List<CrewCustomResponse.CrewChallengePreview> crewChallengePreviewList = challengeList.stream()
                .map(CrewConverter::toChallengePreview).toList();
        return CrewCustomResponse.CrewChallengePreviewList.builder()
                .crewChallengePreviewList(crewChallengePreviewList)
                .build();
    }

    public static CrewCustomResponse.CrewChallengePreview toChallengePreview(Challenge challenge) {
        return CrewCustomResponse.CrewChallengePreview.builder()
                .startAt(challenge.getStartAt())
                .untilWhen(challenge.getUntilWhen())
                .challengeName(challenge.getName())
                .build();
    }
}
