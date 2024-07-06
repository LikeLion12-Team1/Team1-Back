package com.magnetic.domain.user.repository;

import com.magnetic.domain.user.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
}


