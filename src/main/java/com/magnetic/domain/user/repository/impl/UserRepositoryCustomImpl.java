package com.magnetic.domain.user.repository.impl;

import com.magnetic.domain.challenge.entity.QChallenge;
import com.magnetic.domain.crew.entity.QCrew;
import com.magnetic.domain.user.dto.MyPageResponseDto;
import com.magnetic.domain.user.entity.QUserChallenge;
import com.magnetic.domain.user.entity.QUserCrew;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.repository.UserRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MyPageResponseDto.MyChallengePreview> findMyChallenge(User user) {
        QChallenge challenge = QChallenge.challenge;
        QUserChallenge userChallenge = QUserChallenge.userChallenge;

        return queryFactory
                .select(Projections.constructor(MyPageResponseDto.MyChallengePreview.class,
                        challenge.challengeId,
                        challenge.name,
                        challenge.startAt,
                        challenge.untilWhen,
                        challenge.requiredVerification,
                        userChallenge.verificationCount))
                .from(challenge).leftJoin(userChallenge).on(challenge.challengeId.eq(userChallenge.challenge.challengeId))
                .orderBy(challenge.startAt.desc())
                .fetch();
    }

    @Override
    public List<MyPageResponseDto.MyCrewPreview> findMyCrew(User user) {
        QCrew crew = QCrew.crew;
        QUserCrew userCrew = QUserCrew.userCrew;

        return queryFactory
                .select(Projections.constructor(MyPageResponseDto.MyCrewPreview.class,
                        crew.crewId,
                        crew.name,
                        userCrew.createdAt,
                        userCrew.inactiveDate))
                .from(userCrew)
                .leftJoin(userCrew.crew, crew)
                .where(userCrew.user.eq(user))
                .orderBy(userCrew.createdAt.desc())
                .fetch();
    }
}
