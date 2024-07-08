package com.magnetic.domain.plant.repository;

import com.magnetic.domain.plant.dto.PlantResponse;
import com.magnetic.domain.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepositoryCustom {
    List<PlantResponse.PlantPreviewDto> findAllUnlockedPlantByUser(User user);
}
