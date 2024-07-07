package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.Like;
import com.magnetic.domain.crew.entity.Post;
import com.magnetic.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    //있는지 없는지 검토
    boolean existsByUserAndPost(User user, Post post);
    //삭제
    void deleteByUserAndPost(User user, Post post);

}
