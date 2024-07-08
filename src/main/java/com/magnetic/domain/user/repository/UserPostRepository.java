package com.magnetic.domain.user.repository;

import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.user.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPostRepository extends JpaRepository<UserPost, Long> {

    @Query("SELECT a.user FROM UserPost a WHERE a.post.postId = :postId")
    List<Post> findAllPostByCrewName(@Param("crewName") String crewName);
}
