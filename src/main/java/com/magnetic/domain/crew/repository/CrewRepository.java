package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    boolean existsByName(String crewName);

    Optional<Crew> findByName(String crewName);
}
