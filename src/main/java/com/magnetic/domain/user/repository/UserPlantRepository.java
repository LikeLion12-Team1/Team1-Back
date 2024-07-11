package com.magnetic.domain.user.repository;

import com.magnetic.domain.plant.entity.Plant;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserPlantRepository extends JpaRepository<UserPlant, Long> {

    UserPlant findUserPlantByUserAndPlant(User user, Plant plant);

    @Query("SELECT a.plant.plantId FROM UserPlant a WHERE a.user = :user")
    Long findMainPlantId(User user);
}
