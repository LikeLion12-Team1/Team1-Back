package com.magnetic.domain.challenge.repository.impl;

import com.magnetic.domain.challenge.dto.ChallengeResponseDto;
import com.magnetic.domain.challenge.entity.QChallenge;
import com.magnetic.domain.challenge.repository.ChallengeRepositoryCustom;
import com.magnetic.domain.crew.entity.QCrewChallenge;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChallengeRepositoryImpl implements ChallengeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChallengeResponseDto.ChallengePreviewDto> findChallenges(String region, String sportsCategory) {
        QChallenge challenge = QChallenge.challenge;
        QCrewChallenge crewChallenge = QCrewChallenge.crewChallenge;
        BooleanBuilder builder = new BooleanBuilder();

        if (region != null && !region.isEmpty()) {
            builder.and(challenge.region.eq(region));
        }

        if (sportsCategory != null && !sportsCategory.isEmpty()) {
            builder.and(challenge.sportsCategory.eq(sportsCategory));
        }

        return queryFactory
                .select(Projections.constructor(ChallengeResponseDto.ChallengePreviewDto.class,
                        challenge.challengeId,
                        challenge.name,
                        challenge.startAt,
                        challenge.untilWhen,
                        challenge.notice,
                        crewChallenge.count()
                ))
                .from(challenge)
                .leftJoin(crewChallenge).on(challenge.challengeId.eq(crewChallenge.challenge.challengeId))
                .where(builder)
                .groupBy(challenge.challengeId)
                .fetch();
    }
}
