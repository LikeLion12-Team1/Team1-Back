package com.magnetic.domain.plant.service;

import com.magnetic.domain.plant.dto.PlantResponse;
import com.magnetic.domain.plant.entity.Plant;
import com.magnetic.domain.plant.repository.PlantRepository;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserPlant;
import com.magnetic.domain.user.repository.UserPlantRepository;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.PlantHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;
    private final UserPlantRepository userPlantRepository;

    public PlantResponse.PlantPreviewListDto getUnlockedPlantList(User user) {
        List<PlantResponse.PlantPreviewDto> allPlantByUser = plantRepository.findAllUnlockedPlantByUser(user);
        return PlantResponse.PlantPreviewListDto.builder()
                .plantPreviewDtoList(allPlantByUser)
                .build();
    }

    public String setMainPlant(Long plantId, Long previousMainPlantId, User user) {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new PlantHandler(ErrorStatus._NOT_FOUND_PLANT));
        UserPlant userPlant = userPlantRepository.findUserPlantByUserAndPlant(user, plant);
        userPlant.setMain();

        Plant preMainPlant = plantRepository.findById(previousMainPlantId)
                .orElseThrow(() -> new PlantHandler(ErrorStatus._NOT_FOUND_PLANT));
        UserPlant preUserPlant = userPlantRepository.findUserPlantByUserAndPlant(user, preMainPlant);
        preUserPlant.undoMain();

        userPlantRepository.save(userPlant);
        userPlantRepository.save(preUserPlant);

        return "메인 식물이 변경되었습니다.";
    }

    public String unlockPlant(Long plantId, User user) {
        //TODO 챌린지 달성 여부 확인 필요


        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new PlantHandler(ErrorStatus._NOT_FOUND_PLANT));
        UserPlant userPlant = userPlantRepository.findUserPlantByUserAndPlant(user, plant);
        userPlant.unlock();
        userPlantRepository.save(userPlant);
        return "해금되었습니다.";
    }
}
