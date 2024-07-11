package com.magnetic.domain.user.repository;

import com.magnetic.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    //Optional<User> findByNickname(String nickname);

    @Query("SELECT u.nickname, mp.plantId"
            + "FROM User u "
            + "JOIN UserCrew uc ON u.userId = uc.user.userId "
            + "JOIN Crew c ON uc.crew.crewId = c.crewId "
            + "JOIN UserPlant up ON u.userId = up.user.userId "
            + "JOIN Plant mp ON up.plant.plantId = mp.plantId "
            + "WHERE up.isMain = 1 AND c.crewId = :crewId")
    List<CrewPlantResponseDto> findCrewUserMainPlants(@Param("crewId") Long crewId);
}
