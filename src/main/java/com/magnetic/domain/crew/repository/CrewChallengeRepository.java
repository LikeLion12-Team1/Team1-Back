package com.magnetic.domain.crew.repository;

import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.crew.entity.CrewChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrewChallengeRepository extends JpaRepository<CrewChallenge, Long> {

    @Query("SELECT a.challenge FROM CrewChallenge a WHERE a.crew = :crew")
    List<Challenge> findAllChallengeByCrew(@Param("crew") Crew crew);

    @Query("SELECT a.crew FROM CrewChallenge a WHERE a.challenge = :challenge")
    List<Crew> findAllCrewByChallenge(@Param("challenge") Challenge challenge);
}
