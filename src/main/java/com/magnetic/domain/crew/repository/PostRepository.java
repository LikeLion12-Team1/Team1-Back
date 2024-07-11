package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Modifying
    @Query("UPDATE Post p SET p.likeCount = p.likeCount + :amount WHERE p.postId = :postId")
    void updateLikeCount(@Param("postId") Long postId, @Param("amount") int amount);
}

