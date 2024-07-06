package com.magnetic.domain.user.service;

import com.magnetic.domain.user.dto.MyPageResponseDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.repository.UserChallengeRepository;
import com.magnetic.domain.user.repository.UserCrewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final UserCrewRepository userCrewRepository;
    private final UserChallengeRepository userChallengeRepository;

    //TODO QueryDsl방식으로 변경
    public MyPageResponseDto.MyPagePreview getMyPage(User user) {
        return MyPageResponseDto.MyPagePreview.builder()
                .build();
    }
}
