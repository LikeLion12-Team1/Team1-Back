package com.magnetic.domain.user.repository;

import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCrewRepository extends JpaRepository<UserCrew, Long> {

    @Query("SELECT a.crew FROM UserCrew a WHERE a.user = :user")
    List<Crew> findAllCrewByUser(@Param("user") User user);

    @Modifying
    @Query("DELETE FROM UserCrew a WHERE a.user = :user AND a.crew.name = :crewName")
    int deleteUserCrewByUserAndCrewName(@Param("user") User user, @Param("crewName") String crewName);
}
