package com.magnetic.domain.plant.service;

import com.magnetic.domain.plant.dto.PlantResponse;
import com.magnetic.domain.plant.entity.Plant;
import com.magnetic.domain.plant.repository.PlantRepository;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserPlant;
import com.magnetic.domain.user.repository.UserPlantRepository;
import com.magnetic.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    public PlantResponse.PlantPreviewListDto getUnlockedPlantList(User user) {
        List<PlantResponse.PlantPreviewDto> allPlantByUser = plantRepository.findAllUnlockedPlantByUser(user);
        Long mainPlantId = userPlantRepository.findMainPlantId(user);
        return PlantResponse.PlantPreviewListDto.builder()
                .mainPlantId(mainPlantId)
                .plantPreviewDtoList(allPlantByUser)
                .userCount(user.getPlantToken())
                .build();
    }

    public String setMainPlant(Long newMainId, User user) {
        Plant newMainPlant = plantRepository.findById(newMainId)
                        .orElseThrow(() -> new PlantHandler(ErrorStatus._NOT_FOUND_PLANT));
        Plant preMainPlant = userPlantRepository.findMainPlant(user);

        UserPlant newUserPlant = userPlantRepository.findUserPlantByUserAndPlant(user, newMainPlant);
        newUserPlant.setMain();
        userPlantRepository.save(newUserPlant);

        UserPlant preUserPlant = userPlantRepository.findUserPlantByUserAndPlant(user, preMainPlant);
        preUserPlant.undoMain();
        userPlantRepository.save(preUserPlant);

        return "메인 식물이 변경되었습니다.";
    }

    public String unlockPlant(Long plantId, User user) {
        // 토큰 지불
        user.pay();
        userRepository.save(user);

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new PlantHandler(ErrorStatus._NOT_FOUND_PLANT));
        UserPlant userPlant = UserPlant.builder()
                .user(user)
                .plant(plant)
                .isLocked((byte) 1)
                .isMain((byte) 0)
                .build();

        userPlantRepository.save(userPlant);

        return "해금되었습니다.";
    }

    public Long getMainPlant(User user) {
        return userPlantRepository.findMainPlantId(user);
    }
}
