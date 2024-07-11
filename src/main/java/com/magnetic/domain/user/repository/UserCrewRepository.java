package com.magnetic.domain.user.repository;

import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCrewRepository extends JpaRepository<UserCrew, Long> {

    @Query("SELECT a.crew FROM UserCrew a WHERE a.user = :user")
    List<Crew> findAllCrewByUser(@Param("user") User user);

    @Query("SELECT a.user FROM UserCrew a WHERE a.crew.name = :crewName")
    List<User> findAllUserByCrewName(@Param("crew") String crewName);

    @Query("SELECT a.crew " +
            "FROM UserCrew a " +
            "WHERE a.user = :user AND a.crew NOT IN (" +
            "SELECT b.crew " +
            "FROM CrewChallenge b " +
            "WHERE b.challenge.challengeId = :challengeId)")
    List<Crew> findAllCrewByUserAndChallenge(@Param("user") User user, @Param("challengeId") Long challengeId);

    Optional<UserCrew> findByUserAndCrew(User kickMember, Crew crew);

    //크루 가입에 사용
    @Query("SELECT a " +
            "FROM UserCrew a " +
            "WHERE a.user.userId = :userId AND a.crew.crewId = :crewId")
    Optional<UserCrew> findByUserIdAndCrewId(@Param("userId") Long userId, @Param("crewId") Long crewId);

    Long countAllByCrew(Crew crew);
}

