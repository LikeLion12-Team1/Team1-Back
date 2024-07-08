package com.magnetic.domain.user.converter;

import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.user.dto.MyPageResponseDto;
import com.magnetic.domain.user.dto.UserRequestDto;
import com.magnetic.domain.user.dto.UserResponseDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserChallenge;

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

    public static MyPageResponseDto.AdminCommunityPreviewList toAdminCommunityPreviewList(List<Post> postList) {
        List<MyPageResponseDto.AdminCommunityPreview> adminCommunityPreviewList = postList.stream()
                .map(UserConverter::toAdminCommunityPreview).toList();

        return MyPageResponseDto.AdminCommunityPreviewList.builder()
                .adminCommunityPreviewList(adminCommunityPreviewList)
                .build();
    }
    public static MyPageResponseDto.AdminCommunityPreview toAdminCommunityPreview(Post post) {
        return MyPageResponseDto.AdminCommunityPreview.builder()
                .authorId(post.getUser().getUserId())
                .postId(post.getPostId())
                .createdAt(post.getCreatedAt())
                .category(post.getCategory())
                .build();
    }

    public static MyPageResponseDto.AdminMemberPreviewList toAdminMemberPreviewList(List<User> userList) {
        List<MyPageResponseDto.AdminMemberPreview> adminMemberPreviewList = userList.stream()
                .map(UserConverter::toAdminMemberPreview).toList();

        return MyPageResponseDto.AdminMemberPreviewList.builder()
                .adminMemberPreviewList(adminMemberPreviewList)
                .build();
    }

    public static MyPageResponseDto.AdminMemberPreview toAdminMemberPreview(User user) {
        return MyPageResponseDto.AdminMemberPreview.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .build();
    }

    public static MyPageResponseDto.VerifiedResult toVerifiedResult(Post post, UserChallenge userChallenge) {
        return MyPageResponseDto.VerifiedResult.builder()
                .isVerified(post.getIsVerified())
                .verificationCount(userChallenge.getVerificationCount())
                .build();
    }
}
