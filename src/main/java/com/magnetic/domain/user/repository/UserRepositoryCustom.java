package com.magnetic.domain.user.repository;

import com.magnetic.domain.user.dto.MyPageResponseDto;
import com.magnetic.domain.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryCustom {

    List<MyPageResponseDto.MyChallengePreview> findMyChallenge(
            User user
    );

    List<MyPageResponseDto.MyCrewPreview> findMyCrew(
            User user
    );
}
