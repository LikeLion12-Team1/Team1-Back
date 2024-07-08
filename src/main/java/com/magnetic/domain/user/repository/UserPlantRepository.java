package com.magnetic.domain.user.repository;

import com.magnetic.domain.plant.entity.Plant;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserPlant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPlantRepository extends JpaRepository<UserPlant, Long> {

    UserPlant findUserPlantByUserAndPlant(User user, Plant plant);
}
