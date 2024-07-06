package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
