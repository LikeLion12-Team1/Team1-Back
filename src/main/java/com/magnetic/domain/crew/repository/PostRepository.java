package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

