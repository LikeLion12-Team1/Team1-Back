package com.magnetic.domain.user.service;

import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.challenge.repository.ChallengeRepository;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.crew.repository.CrewPostRepository;
import com.magnetic.domain.crew.repository.CrewRepository;
import com.magnetic.domain.crew.repository.PostRepository;
import com.magnetic.domain.user.converter.UserConverter;
import com.magnetic.domain.user.dto.MyPageResponseDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserChallenge;
import com.magnetic.domain.user.entity.UserCrew;
import com.magnetic.domain.user.repository.UserChallengeRepository;
import com.magnetic.domain.user.repository.UserCrewRepository;
import com.magnetic.domain.user.repository.UserRepository;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.ChallengeHandler;
import com.magnetic.global.common.exception.handler.CrewHandler;
import com.magnetic.global.common.exception.handler.PostHandler;
import com.magnetic.global.common.exception.handler.UserHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final CrewRepository crewRepository;
    private final PostRepository postRepository;
    private final ChallengeRepository challengeRepository;
    private final UserChallengeRepository userChallengeRepository;
    private final CrewPostRepository crewPostRepository;
    private final UserCrewRepository userCrewRepository;

    public MyPageResponseDto.MyPagePreview getMyPage(User user) {
        List<MyPageResponseDto.MyChallengePreview> myChallengePreviewList = userRepository.findMyChallenge(user);
        List<MyPageResponseDto.MyCrewPreview> myCrewPreviewList = userRepository.findMyCrew(user);

        return MyPageResponseDto.MyPagePreview.builder()
                .myCrewPreviewList(myCrewPreviewList)
                .myChallengePreviewList(myChallengePreviewList)
                .userCount(user.getPlantToken())
                .build();
    }

    public MyPageResponseDto.AdminMyPagePreview getAdminMyPage(String crewName) {
        List<Post> postList = crewPostRepository.findAllPostByCrewName(crewName);
        List<MyPageResponseDto.AdminCommunityPreview> adminCommunityPreviewList = postList.stream()
                .map(UserConverter::toAdminCommunityPreview).toList();

        List<User> userList = userCrewRepository.findAllUserByCrewName(crewName);
        List<MyPageResponseDto.AdminMemberPreview> adminMemberPreviewList = userList.stream()
                .map(UserConverter::toAdminMemberPreview).toList();

        return MyPageResponseDto.AdminMyPagePreview.builder()
                .adminCommunityPreviewList(adminCommunityPreviewList)
                .adminMemberPreviewList(adminMemberPreviewList)
                .build();
    }

    public MyPageResponseDto.VerifiedResult verifyChallengePost(
            Long challengeId, Long postId) {
        // 글 상태 verified로 변경
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostHandler(ErrorStatus._NOT_FOUND_POST));
        post.verify();
        Post savedPost = postRepository.save(post);

        // 챌린지 충족도 변경
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new ChallengeHandler(ErrorStatus._NOT_FOUND_CHALLENGE));
        UserChallenge userChallenge = userChallengeRepository.findByUserAndChallenge(post.getUser(), challenge)
                .orElseThrow(() -> new ChallengeHandler(ErrorStatus._NOT_CHALLENGING));
        userChallenge.verify();
        UserChallenge savedUserChallenge = userChallengeRepository.save(userChallenge);

        return UserConverter.toVerifiedResult(savedPost, savedUserChallenge);
    }

    public void kickMember(String crewName, Long kickMemberId) {
        User kickMember = userRepository.findById(kickMemberId)
                .orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        Crew crew = crewRepository.findByName(crewName)
                .orElseThrow(() -> new CrewHandler(ErrorStatus._NOT_FOUND_CREW));
        UserCrew userCrew = userCrewRepository.findByUserAndCrew(kickMember, crew)
                .orElseThrow(() -> new CrewHandler(ErrorStatus._NOT_FOUND_MEMBER));
        userCrewRepository.delete(userCrew);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public String doneChallenge(User user, Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new ChallengeHandler(ErrorStatus._NOT_FOUND_CHALLENGE));
        Long requireCount = challenge.getRequiredVerification();

        UserChallenge userChallenge = userChallengeRepository.findByUserAndChallengeOnChallenging(user, challenge)
                .orElseThrow(() -> new ChallengeHandler(ErrorStatus._ALREADY_PAID));
        Long verificationCount = userChallenge.getVerificationCount();

        if (requireCount >= verificationCount) {
            userChallenge.paidUp();
            userChallengeRepository.save(userChallenge);

            user.receiveToken();
            userRepository.save(user);

            return "챌린지 달성 보상이 지급되었습니다.";
        } else {
            return "챌린지를 아직 달성하지 못했습니다.";
        }

    }
}
