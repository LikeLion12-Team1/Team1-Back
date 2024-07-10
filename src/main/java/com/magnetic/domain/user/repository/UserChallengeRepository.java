package com.magnetic.domain.user.repository;

import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    Optional<UserChallenge> findByUserAndChallenge(User user, Challenge challenge);

    @Query("SELECT a " +
            "FROM UserChallenge a " +
            "WHERE a.user = :user " +
            "AND a.challenge = :challenge " +
            "AND a.isPaidUp != 1")
    Optional<UserChallenge> findByUserAndChallengeOnChallenging(
            @Param("user") User user, @Param("challenge") Challenge challenge);
}


