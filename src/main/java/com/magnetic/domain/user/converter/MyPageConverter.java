package com.magnetic.domain.user.converter;

import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.user.dto.MyPageResponseDto;

import java.util.List;

public class MyPageConverter {

//    public static MyPageResponseDto.MyChallengePreviewList toMyChallengePreviewList(List<Challenge> challengeList) {
//        List<MyPageResponseDto.MyChallengePreview> myChallengePreviewList = challengeList.stream()
//                .map(MyPageConverter::toMyChallengePreview).toList();
//
//        return MyPageResponseDto.MyChallengePreviewList.builder()
//                .myChallengePreviewList(myChallengePreviewList)
//                .build();
//    }
//
//    public static MyPageResponseDto.MyChallengePreview toMyChallengePreview(Challenge challenge) {
//        return MyPageResponseDto.MyChallengePreview.builder()
//                .challengeId(challenge.getChallengeId())
//                .challengeName(challenge.getName())
//                .startAt(challenge.getStartAt())
//                .untilWhen(challenge.getUntilWhen())
//                .status("진행중")
//                .build();
//    }

//    public static MyPageResponseDto.MyCrewPreviewList toMyCrewPreviewList(List<Crew> crewList) {
//        List<MyPageResponseDto.MyCrewPreview> myCrewPreviewList = crewList.stream()
//                .map(MyPageConverter::toMyCrewPreview).toList();
//    }
//
//    public static MyPageResponseDto.MyCrewPreview toMyCrewPreview(Crew crew) {
//        return MyPageResponseDto.MyCrewPreview.builder()
//                .crewId(crew.getCrewId())
//                .crewName(crew.getName())
//                .startAt(crew.get)
//                ;
//    }
}
