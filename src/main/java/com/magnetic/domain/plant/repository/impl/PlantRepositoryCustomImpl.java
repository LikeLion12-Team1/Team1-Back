package com.magnetic.domain.plant.repository.impl;

import com.magnetic.domain.plant.dto.PlantResponse;
import com.magnetic.domain.plant.entity.QPlant;
import com.magnetic.domain.plant.repository.PlantRepositoryCustom;
import com.magnetic.domain.user.entity.QUserPlant;
import com.magnetic.domain.user.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlantRepositoryCustomImpl implements PlantRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PlantResponse.PlantPreviewDto> findAllUnlockedPlantByUser(User user) {
        QPlant plant = QPlant.plant;
        QUserPlant userPlant = QUserPlant.userPlant;

        return queryFactory
                .select(Projections.constructor(PlantResponse.PlantPreviewDto.class,
                        plant.type,
                        plant.name,
                        userPlant.isLocked))
                .from(plant)
                .leftJoin(userPlant).on(plant.plantId.eq(userPlant.plant.plantId))
                .where(userPlant.isLocked.eq((byte) 1))
                .fetch();
    }
}
