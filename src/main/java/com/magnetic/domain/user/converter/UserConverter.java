package com.magnetic.domain.user.converter;

import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.user.dto.UserRequestDto;
import com.magnetic.domain.user.dto.UserResponseDto;
import com.magnetic.domain.user.entity.User;

import java.time.LocalDate;
import java.util.List;

public class UserConverter {

    public static User toUser(UserRequestDto.Profile request) {
        return User.builder()
                .nickname(request.nickname())
                .region(request.region())
                .build();
    }

    public static UserResponseDto.ProfilePreview toProfilePreviewDto(User user, List<Crew> crewList) {
        return UserResponseDto.ProfilePreview.builder()
                .nickname(user.getNickname())
                .region(user.getRegion())
                .profileImg(user.getProfileImg())
                .crewPreviewList(toCrewPreviewList(crewList))
                .build();
    }

    public static UserResponseDto.CrewPreviewList toCrewPreviewList(List<Crew> crewList) {
        List<UserResponseDto.CrewPreview> crewPreviewList = crewList.stream()
                .map(UserConverter::toCrewPreview).toList();
        return UserResponseDto.CrewPreviewList.builder()
                .crewPreviewList(crewPreviewList)
                .build();
    }

    public static UserResponseDto.CrewPreview toCrewPreview(Crew crew) {
        return UserResponseDto.CrewPreview.builder()
                .crewName(crew.getName())
                .build();
    }
}
