package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.CrewPost;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrewPostRepository extends JpaRepository<CrewPost, Long> {
}
