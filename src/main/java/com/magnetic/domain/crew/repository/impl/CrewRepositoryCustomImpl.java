package com.magnetic.domain.crew.repository.impl;

import com.magnetic.domain.crew.dto.crewdto.CrewResponseDto;
import com.magnetic.domain.crew.dto.postdto.QueryPostResponse;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.entity.QCrew;
import com.magnetic.domain.crew.entity.QCrewPost;
import com.magnetic.domain.crew.entity.QPost;
import com.magnetic.domain.crew.repository.CrewRepositoryCustom;
import com.magnetic.domain.user.entity.QUser;
import com.magnetic.domain.user.entity.QUserCrew;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CrewRepositoryCustomImpl implements CrewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CrewResponseDto> findAllCrew(String region, String category) {
        QCrew crew = QCrew.crew;
        QUserCrew userCrew = QUserCrew.userCrew;
        BooleanBuilder builder = new BooleanBuilder();

        if (region != null && !region.isEmpty()) {
            builder.and(crew.region.eq(region));
        }

        if (category != null && !category.isEmpty()) {
            builder.and(crew.sportsCategory.eq(category));
        }

        return queryFactory
                .select(Projections.constructor(CrewResponseDto.class,
                        crew.crewImg,
                        crew.crewId,
                        crew.name,
                        crew.region,
                        crew.sportsCategory,
                        crew.createdAt))
                .from(crew).leftJoin(userCrew).on(crew.crewId.eq(userCrew.crew.crewId))
                .where(builder)
                .orderBy(crew.crewId.desc())
                .fetch();
    }

    @Override
    public List<QueryPostResponse.PostPreviewDto> findAllPost(Crew crew) {
        QPost post = QPost.post;
        QUser user = QUser.user;
        QCrewPost crewPost = QCrewPost.crewPost;

        return queryFactory
                .select(Projections.constructor(QueryPostResponse.PostPreviewDto.class,
                        post.postId,
                        post.photoUrl,
                        post.createdAt,
                        user.nickname,
                        user.profileImg,
                        post.content,
                        post.category,
                        post.likeCount))
                .from(post)
                .leftJoin(post.user, user)
                .leftJoin(crewPost).on(post.eq(crewPost.post))
                .where(crewPost.crew.eq(crew))
                .fetch();
    }
}
