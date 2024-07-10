package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long>, CrewRepositoryCustom {
    boolean existsByName(String crewName);

    List<Crew> findByRegion(String region);
    List<Crew> findBySportsCategory(String sportsCategory);

    Optional<Crew> findByName(String crewName);
}
