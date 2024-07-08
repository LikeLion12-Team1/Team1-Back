package com.magnetic.domain.user.repository;

import com.magnetic.domain.challenge.entity.Challenge;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    Optional<UserChallenge> findByUserAndChallenge(User user, Challenge challenge);
}


