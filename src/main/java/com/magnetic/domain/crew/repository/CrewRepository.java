package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew, Long> {
}
