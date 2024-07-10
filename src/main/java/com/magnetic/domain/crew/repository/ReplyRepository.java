package com.magnetic.domain.crew.repository;

import com.magnetic.domain.crew.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("SELECT r FROM Reply r WHERE r.post.postId = :postId")
    List<Reply> findAllByPostId(Long postId);
}
