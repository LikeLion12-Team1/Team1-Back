package com.magnetic.domain.user.repository;

import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserCrewRepository extends JpaRepository<UserCrew, Long> {

    @Query("SELECT a.crew FROM UserCrew a WHERE a.user = :user")
    List<Crew> findAllCrewByUser(@Param("user") User user);

    @Query("SELECT a.user FROM UserCrew a WHERE a.crew.name = :crewName")
    List<User> findAllUserByCrewName(@Param("crew") String crewName);

    @Modifying
    @Query("UPDATE UserCrew a " +
            "SET a.status = 'inactive', a.inactiveDate = :now " +
            "WHERE a.user = :user AND a.crew.name = :crewName")
    void updateUserCrewStatusToInactive(
            @Param("user") User user, @Param("crewName") String crewName, @Param("now") LocalDate now);

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
}

