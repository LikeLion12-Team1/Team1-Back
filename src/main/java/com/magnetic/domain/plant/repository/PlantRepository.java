package com.magnetic.domain.plant.repository;

import com.magnetic.domain.plant.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>, PlantRepositoryCustom {
}
