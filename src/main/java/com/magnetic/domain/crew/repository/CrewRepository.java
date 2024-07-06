package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.dto.response.CrewResponseDto;
import com.magnetic.domain.crew.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    boolean existsByName(String crewName);

    List<Crew> findByRegion(String region);
    List<Crew> findBySportsCategory(String sportsCategory);
}
