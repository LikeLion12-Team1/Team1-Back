package com.magnetic.domain.challenge.repository;

import com.magnetic.domain.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long>, ChallengeRepositoryCustom {
}
