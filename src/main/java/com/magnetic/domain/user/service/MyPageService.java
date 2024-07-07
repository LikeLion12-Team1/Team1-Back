package com.magnetic.domain.user.service;

import com.magnetic.domain.user.dto.MyPageResponseDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;

    public MyPageResponseDto.MyPagePreview getMyPage(User user) {
        List<MyPageResponseDto.MyChallengePreview> myChallengePreviewList = userRepository.findMyChallenge(user);
        List<MyPageResponseDto.MyCrewPreview> myCrewPreviewList = userRepository.findMyCrew(user);

        return MyPageResponseDto.MyPagePreview.builder()
                .myCrewPreviewList(myCrewPreviewList)
                .myChallengePreviewList(myChallengePreviewList)
                .build();
    }
}
